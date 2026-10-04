package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
	
	private long longTimeout = GlobalConstants.LONGTIMEOUT;
	private long shortTimeout = GlobalConstants.SHORTTIMEOUT;
			
	public static BasePage getBasePage() {
		return new BasePage();
	}
	
	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public By getByXpath(String xpathExpression) {
		return By.xpath(xpathExpression);
	}
	
	public WebElement findElement(WebDriver driver, String xpathExpression) {
		return driver.findElement(getByXpath(xpathExpression));
	}
	
	public List<WebElement> findElements(WebDriver driver, String xpathExpression) {
		return driver.findElements(getByXpath(xpathExpression));
	}
	
	public void waitForElementPresent(WebDriver driver, String xpathExpression, long timeoutSecond) {
		new WebDriverWait(driver, timeoutSecond).until(ExpectedConditions.presenceOfElementLocated(getByXpath(xpathExpression)));
	}
	
	public void waitForElementVisible(WebDriver driver, String xpathExpression, long timeoutSecond) {
		new WebDriverWait(driver, timeoutSecond).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathExpression)));
	}
	
	public void waitForElementClickable(WebDriver driver, String xpathExpression, long timeoutSecond) {
		new WebDriverWait(driver, timeoutSecond).until(ExpectedConditions.elementToBeClickable(getByXpath(xpathExpression)));
	}
	
	public void waitForElementInvisible(WebDriver driver, String xpathExpression, long timeoutSecond) {
		new WebDriverWait(driver, timeoutSecond).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathExpression)));
	}
	
	public Alert waitForAlertPresence (WebDriver driver, long timeoutSecond) {
		return new WebDriverWait(driver, timeoutSecond).until(ExpectedConditions.alertIsPresent());
	}
	
	public void clickToElement(WebDriver driver, String xpathExpression, long timeoutSecond) {
		try {
			waitForElementClickable(driver, xpathExpression, timeoutSecond);
			findElement(driver, xpathExpression).click();
		} catch (StaleElementReferenceException e) {
			waitForElementClickable(driver, xpathExpression, timeoutSecond);
			findElement(driver, xpathExpression).click();
		}
		
	}
	
	public void sendKeysToElement(WebDriver driver, String xpathExpression, String valueToSend, long timeoutSecond) {
		waitForElementVisible(driver, xpathExpression, timeoutSecond);
		findElement(driver, xpathExpression).clear();
		findElement(driver, xpathExpression).sendKeys(valueToSend);
	}
	
	public String getText(WebDriver driver, String xpathExpression, long timeoutSecond) {
		waitForElementVisible(driver, xpathExpression, timeoutSecond);
		return findElement(driver, xpathExpression).getText();
	}
	
	public String getAttribute(WebDriver driver, String xpathExpression, String attributeName, long timeoutSecond) {
		waitForElementVisible(driver, xpathExpression, timeoutSecond);
		return findElement(driver, xpathExpression).getAttribute(attributeName);
	}
	
	public boolean elementIsDisplayed(WebDriver driver, String xpathExpression) {
		try {
			return findElement(driver, xpathExpression).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}	
	
	public boolean elementIsEnabled(WebDriver driver, String xpathExpression) {
		return findElement(driver, xpathExpression).isEnabled();
	}	
	
	public boolean elementIsSelected(WebDriver driver, String xpathExpression) {
		return findElement(driver, xpathExpression).isSelected();
	}	
	
	public int getElementCount(WebDriver driver, String xpathExpression) {
		return findElements(driver, xpathExpression).size();
	}	
	
	public void selectDropdownByText(WebDriver driver, String xpathExpression, String text, long timeoutSecond) {
		waitForElementClickable(driver, xpathExpression, timeoutSecond);
		new Select(findElement(driver, xpathExpression)).selectByValue(text);
	}	
	
	public void selectDropdownByIndex(WebDriver driver, String xpathExpression, int index, long timeoutSecond) {
		waitForElementClickable(driver, xpathExpression, timeoutSecond);
		new Select(findElement(driver, xpathExpression)).selectByIndex(index);
	}	
	
	public String getSelectedText(WebDriver driver, String xpathExpression) {
		return new Select(findElement(driver, xpathExpression)).getFirstSelectedOption().getText();
	}	
	
	public void checkToCheckboxOrRadio(WebDriver driver, String xpathExpression) {
		if (!elementIsSelected(driver, xpathExpression)) {
			findElement(driver, xpathExpression).click();
		}
	}	
	
	public void unCheckToCheckbox(WebDriver driver, String xpathExpression) {
		if (elementIsSelected(driver, xpathExpression)) {
			findElement(driver, xpathExpression).click();
		}
	}	
	
	public void acceptToAlert(WebDriver driver, long timeoutSecond) {
		waitForAlertPresence(driver, timeoutSecond).accept();
	}
	
	public void dismissAlert(WebDriver driver, long timeoutSecond) {
		waitForAlertPresence(driver, timeoutSecond).dismiss();
	}
	
	public String getAlertText(WebDriver driver, long timeoutSecond) {
		return waitForAlertPresence(driver, timeoutSecond).getText();
	}
	
	public void typeIntoAlert(WebDriver driver, String valueToSend, long timeoutSecond) {
		waitForAlertPresence(driver, timeoutSecond).sendKeys(valueToSend);
	}
	
	public void switchToFrame(WebDriver driver, String xpathExpression) {
		(new WebDriverWait(driver, longTimeout)).until(
				ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByXpath(xpathExpression)));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public String getCurrentWindowID(WebDriver driver) {
		return driver.getWindowHandle();
	}
	
	public Set<String> getWindowIDs(WebDriver driver) {
		return driver.getWindowHandles();
	}
	
	public void switchToNewWindow(WebDriver driver, Set<String> oldWindowIDs) {
		String newWindowID = new WebDriverWait(driver, shortTimeout).until(d -> {
			Set<String> getAllWindowIDs = d.getWindowHandles();
			
			for (String windowID : getAllWindowIDs) {
				if(!oldWindowIDs.contains(windowID)) {
					return windowID;
				}
			}
			return null;
		});
		
		switchToWindowByID(driver, newWindowID);
	}
	
	public void switchToWindowByID(WebDriver driver, String windowID) {
	    driver.switchTo().window(windowID);
	}
	
	public void switchToWindowByTitle(WebDriver driver, String title) {
		String originalWindowID = getCurrentWindowID(driver);
		
		try {
			new WebDriverWait(driver, shortTimeout).until(d -> {
				for (String WindowID : getWindowIDs(d)) {
					switchToWindowByID(d, WindowID);
					
					if (getPageTitle(d).equals(title)) {
						return true;
					}
				}
				return false;
			});
			
		} catch (TimeoutException e) {
			switchToWindowByID(driver, originalWindowID);
			throw e;
		}
		
	}
	
	public void closeCurrentWindow(WebDriver driver) {
	    driver.close();
	}
	
	public void closeOtherWindows(WebDriver driver, String windowIDToKeep) {
		Set<String> allCurrentWindowIDs = getWindowIDs(driver);
		
		if (!allCurrentWindowIDs.contains(windowIDToKeep)) {
			throw new NoSuchWindowException("Window is not exists" + windowIDToKeep);
		}
		
		for (String windowID : allCurrentWindowIDs) {
			if (!windowID.equals(windowIDToKeep)) {
				switchToWindowByID(driver, windowID);
				closeCurrentWindow(driver);
			}
		}
		
		switchToWindowByID(driver, windowIDToKeep);
	}
	
	public void hoverToElement(WebDriver driver, String xpathExpression, long timeoutSecond) {
		waitForElementVisible(driver, xpathExpression, timeoutSecond);
		new Actions(driver).moveToElement(findElement(driver, xpathExpression)).perform();
	}
	
	public void doubleClickToElement(WebDriver driver, String xpathExpression, long timeoutSecond) {
		waitForElementClickable(driver, xpathExpression, timeoutSecond);
		new Actions(driver).doubleClick(findElement(driver, xpathExpression)).perform();
	}
	
	public void rightClickToElement(WebDriver driver, String xpathExpression, long timeoutSecond) {
		waitForElementClickable(driver, xpathExpression, timeoutSecond);
		new Actions(driver).contextClick(findElement(driver, xpathExpression)).perform();
	}
	
	public void dragAndDropToElement(WebDriver driver, String sourceXpath, String targetXpath) {
		new Actions(driver).dragAndDrop(findElement(driver, sourceXpath), findElement(driver, targetXpath)).perform();
	}

	public void sendKeyBoardToElement(WebDriver driver, String xpathExpression, Keys key) {
		new Actions(driver).sendKeys(findElement(driver, xpathExpression), key).perform();
	}
	
	public void scrollToElement(WebDriver driver, String xpathExpression, boolean bool, long timeoutSecond) {
		waitForElementPresent(driver, xpathExpression, timeoutSecond);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(" + String.valueOf(bool) + ")", 
				findElement(driver, xpathExpression));
	}
	
	public void scrollToTop(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
	}
	
	public void scrollToBottom(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
	}
	
	public void navigateToUrl(WebDriver driver, String expectedUrl) {
		((JavascriptExecutor) driver).executeScript("document.location = '" + expectedUrl + "'");
	}

	public void clickOnElement(WebDriver driver, String xpathExpression) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", findElement(driver, xpathExpression));
	}

	public void scrollToElement(WebDriver driver, String xpathExpression, boolean boolValue) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(" + boolValue + ")",
				findElement(driver, xpathExpression));
	}

	public boolean imageIsLoaded(WebDriver driver, String xpathExpression) {
		return (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				findElement(driver, xpathExpression));
	}

	public boolean isPageLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	public void uploadFiles(WebDriver driver, String xpathExpression, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_PATH;
		String fullFileName = "";
		
		for (String fileName : fileNames) {
			fullFileName += filePath + fileName + "\n";
		}
		
		fullFileName = fullFileName.trim();
		waitForElementPresent(driver, xpathExpression, longTimeout);
		findElement(driver, xpathExpression).sendKeys(fullFileName);
	}
	
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
