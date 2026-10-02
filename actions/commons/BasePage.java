package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	private long longTimeout = GlobalConstants.LONGTIMEOUT;
			
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
	
	public void waitForElementPresent(WebDriver driver, String xpathExpression) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.presenceOfElementLocated(getByXpath(xpathExpression)));
	}
	
	public void waitForElementVisible(WebDriver driver, String xpathExpression) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathExpression)));
	}
	
	public void waitForElementClickable(WebDriver driver, String xpathExpression) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByXpath(xpathExpression)));
	}
	
	public void waitForElementInvisible(WebDriver driver, String xpathExpression) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathExpression)));
	}
	
	public Alert waitForAlertPresence (WebDriver driver) {
		return new WebDriverWait(driver, longTimeout).until(ExpectedConditions.alertIsPresent());
	}
	
	public void clickToElement(WebDriver driver, String xpathExpression) {
		waitForElementClickable(driver, xpathExpression);
		findElement(driver, xpathExpression).click();
	}
	
	public void sendKeysToElement(WebDriver driver, String xpathExpression, String valueToSend) {
		waitForElementVisible(driver, xpathExpression);
		findElement(driver, xpathExpression).clear();
		findElement(driver, xpathExpression).sendKeys(valueToSend);
	}
	
	public String getText(WebDriver driver, String xpathExpression) {
		waitForElementVisible(driver, xpathExpression);
		return findElement(driver, xpathExpression).getText();
	}
	
	public String getAttribute(WebDriver driver, String xpathExpression, String attributeName) {
		waitForElementVisible(driver, xpathExpression);
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
	
	public void selectDropdownByText(WebDriver driver, String xpathExpression, String text) {
	}	
	
	public void selectDropdownByIndex(WebDriver driver, String xpathExpression, int index) {
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
	
	public void acceptToAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	public void dismissAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	public void typeIntoAlert(WebDriver driver, String valueToSend) {
		waitForAlertPresence(driver).sendKeys(valueToSend);
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
	
	
	
	public void hoverToElement(WebDriver driver, String xpathExpression) {
		new Actions(driver).moveToElement(findElement(driver, xpathExpression)).perform();
	}
	
	public void doubleClickToElement(WebDriver driver, String xpathExpression) {
		new Actions(driver).doubleClick(findElement(driver, xpathExpression)).perform();
	}
	
	public void rightClickToElement(WebDriver driver, String xpathExpression) {
		new Actions(driver).contextClick(findElement(driver, xpathExpression)).perform();
	}
	
	public void scrollToElement(WebDriver driver, String xpathExpression) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", getByXpath(xpathExpression));
	}
}
