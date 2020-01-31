package config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Element implements WebElement {

    private By _by;
    private Element _parent;
    private RemoteWebElement _webElement;
    private int index = 0;
    private WebElement element;

    public Element(By by) {
        this._by = by;
    }

    public Element(WebElement element) {
        this.element = element;
    }

    private Element(RemoteWebElement webElement) {
        this._webElement = webElement;
    }

    private Element(Element parent, By by) {
        this._parent = parent;
        this._by = by;
    }

    public By get_by() {
        return _by;
    }

    public String getLocatorXpath() {
        return _by.toString().replace("By.xpath: ", "");
    }

    public String getLocatorId() {
        return _by.toString().replace("By.id: ", "");
    }

    public void click() {
        doElementAction((new Runnable() {
            @Override
            public void run() {
                getRemoteElement().click();
            }
        }));
    }

    public void doubleClick() {
        doElementAction((new Runnable() {
            @Override
            public void run() {
                WebElement element = getRemoteElement();
                action().click(element).click(element).perform();
            }
        }));
    }

    public Element moveToElement() {
        doElementAction((new Runnable() {
            @Override
            public void run() {
                WebElement elem = getRemoteElement();
                action().moveToElement(elem).perform();
            }
        }));
        return this;
    }

    public Element moveToElement(final int x, final int y) {
        doElementAction((new Runnable() {
            @Override
            public void run() {
                WebElement elem = getRemoteElement();
                action().moveToElement(elem, x, y).perform();
            }
        }));
        return this;
    }

    public Element moveByOffset(final int x, final int y) {
        doElementAction((new Runnable() {
            @Override
            public void run() {
                action().moveToElement(getRemoteElement()).moveByOffset(x, y).build().perform();
            }
        }));
        return this;
    }

    public Element doubleClickByOffset(final int x, final int y) {
        doElementAction((new Runnable() {
            @Override
            public void run() {
                action().moveByOffset(x, y).click().click().build().perform();
            }
        }));
        return this;
    }

    public Element clickByOffset(final int x, final int y) {
        doElementAction((new Runnable() {
            @Override
            public void run() {
                action().moveByOffset(x, y).click().build().perform();
            }
        }));
        return this;
    }

    public void clickExecute() {
        doElementAction((new Runnable() {
            @Override
            public void run() {
                Driver.getDriver().executeScript("arguments[0].click();", getRemoteElement());
            }
        }));
    }

    public void focus() {
        doElementAction((new Runnable() {
            @Override
            public void run() {
                Driver.getDriver().executeScript("arguments[0].focus();", getRemoteElement());
            }
        }));
    }

    public void unFocus() {
        doElementAction((new Runnable() {
            @Override
            public void run() {
                Driver.getDriver().executeScript("$(arguments[0]).blur();", getRemoteElement());
            }
        }));
    }

    public void setValue(final String value) {
        doElementAction((new Runnable() {
            @Override
            public void run() {
                Driver.getDriver().executeScript(String.format("arguments[0].value='%s'", value), getRemoteElement());
            }
        }));
    }

    public void setAttribute(final String nameAttribute, final String value) {
        doElementAction((new Runnable() {
            @Override
            public void run() {
                Driver.getDriver().executeScript(String.format("arguments[0]." + "setAttribute('%s', '%s')", nameAttribute, value), getRemoteElement());
            }
        }));
    }

    public void submit() {
        doElementAction((new Runnable() {
            @Override
            public void run() {
                getRemoteElement().submit();
            }
        }));
    }

    public void sendKeys(final Keys keys) {
        doElementAction((new Runnable() {
            @Override
            public void run() {
                getRemoteElement().sendKeys(keys);
            }
        }));
    }

    @Override
    public void sendKeys(CharSequence... keys) {

    }

    public void sendKeys(final String keysToSend) {
        doElementAction((new Runnable() {
            @Override
            public void run() {
                getRemoteElement().sendKeys(keysToSend);
            }
        }));
    }

    public void clear() {
        doElementAction((new Runnable() {
            @Override
            public void run() {
                getRemoteElement().clear();
            }
        }));
    }

    public boolean isDisplayed() {
        List<WebElement> list = Driver.getDriver().findElements(this._by);
        if (list.size() == 0) {
            return false;
        } else {
            try {
                return list.get(0).isDisplayed();
            } catch (StaleElementReferenceException stelex) {
                return false;
            }
        }
        //        return doElementAction(Boolean.class, new Callable() {
        //            @Override
        //            public Object call() throws Exception {
        //                return getRemoteElement().isDisplayed();
        //            }
        //        });
    }

    public String getTagName() {
        return doElementAction(String.class, new Callable() {
            @Override
            public Object call() throws Exception {
                return getRemoteElement().getTagName();
            }
        });
    }

    public String getAttribute(final String name) {
        return doElementAction(String.class, new Callable() {
            @Override
            public Object call() throws Exception {
                return getRemoteElement().getAttribute(name);
            }
        });
    }

    public boolean isSelected() {
        return doElementAction(Boolean.class, new Callable() {
            @Override
            public Object call() throws Exception {
                return getRemoteElement().isSelected();
            }
        });
    }

    public boolean isEnabled() {
        return doElementAction(Boolean.class, new Callable() {
            @Override
            public Object call() throws Exception {
                return !getRemoteElement().getAttribute("class").contains("irDisabled");
            }
        });
        //   return this.isEnabled();
    }

    public boolean isDisabled() {
        return !Driver.getDriver().findElement(this.get_by()).isEnabled();
    }

    public String getText() {
        return doElementAction(String.class, new Callable() {
            @Override
            public Object call() throws Exception {
                return getRemoteElement().getText().trim();
            }
        });
    }

    public List<String> getTextList() {
        return doElementAction(List.class, new Callable() {
            @Override
            public Object call() throws Exception {
                List<String> result = new ArrayList<String>();
                for (WebElement element : getRemoteElements()) {
                    result.add(element.getText());
                }
                return result;
            }
        });
    }

    public Point getLocation() {
        return doElementAction(Point.class, new Callable() {
            @Override
            public Object call() throws Exception {
                return getRemoteElement().getLocation();
            }
        });
    }

    public Dimension getSize() {
        return doElementAction(Dimension.class, new Callable() {
            @Override
            public Object call() throws Exception {
                return getRemoteElement().getSize();
            }
        });
    }

    public Rectangle getRect() {
        return doElementAction(Rectangle.class, new Callable() {
            @Override
            public Object call() throws Exception {
                return getRemoteElement().getRect();
            }
        });
    }

    public String getCssValue(final String propertyName) {
        return doElementAction(String.class, new Callable() {
            @Override
            public Object call() throws Exception {
                return getRemoteElement().getCssValue(propertyName);
            }
        });
    }

    public Coordinates getCoordinates() {
        return doElementAction(Coordinates.class, new Callable() {
            @Override
            public Object call() throws Exception {
                return getRemoteElement().getCoordinates();
            }
        });
    }

    private <T> T doElementAction(Class<T> clazz, Callable action) {
        int iterations = 3;
        do {
            try {
                return clazz.cast(action.call());
            } catch (StaleElementReferenceException se) {
            } catch (Exception e) {
                throw new WebDriverException(e.getMessage());
            }
        } while (iterations-- > 0);
        return null;
    }

    private void doElementAction(Runnable action) {
        int iterations = 3;
        do {
            try {
                action.run();
                break;
            } catch (StaleElementReferenceException se) {
            } catch (Exception e) {
                throw new NoSuchElementException(e.getMessage());
            }
        } while (iterations-- > 0);
    }

    public List<WebElement> findElements(By by) {
        return Lists.transform(getRemoteElement().findElements(by), new Function<WebElement, WebElement>() {
            public Element apply(WebElement element) {
                return new Element((RemoteWebElement) element);
            }
        });
    }

    public Element findElement(String elementText, boolean equals) {
        if (equals)
            return new Element(this, By.xpath(String.format("//*[normalize-space(text()) = '%s']", elementText)));
        else
            return new Element(this, By.xpath(String.format("//*[contains(text(),'%s')]", elementText)));
    }

    public Element findElement(String elementText) {
        return findElement(elementText, true);
    }

    public Element findElement(By by) {
        return new Element(this, by);
    }

    public Element byIndex(int index) {
        this.index = index;
        return this;
    }

    public String toString() {
        return getRemoteElement().toString();
    }

    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }

    private RemoteWebElement getRemoteElement() {
        return getRemoteElement(true);
    }

    private List<WebElement> getRemoteElements() {
        if (_parent != null)
            return _parent.getRemoteElement().findElements(_by);
        return Driver.getDriver().findElements(_by);
    }

    private RemoteWebElement getRemoteElement(boolean waitForPresence) {
        if (waitForPresence && _webElement == null)
            DriverWait.waitElementPresent(_by);
        if (_webElement != null)
            return _webElement;
        if (_parent != null)
            return (RemoteWebElement) _parent.getRemoteElement().findElements(_by).get(index);
        return (RemoteWebElement) Driver.getDriver().findElements(_by).get(index);
    }

    private Actions action() {
        return new Actions(Driver.getDriver().remoteWebDriver);
    }

}
