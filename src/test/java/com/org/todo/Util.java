package com.org.todo;

import com.gargoylesoftware.htmlunit.html.DomNode;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.beans.PropertyEditorSupport;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Util {

    //webdriver and webelement objects
    public WebDriver driver;
    public WebElements web;
    private WebElement addField;
    private WebElement addedItem;
    private boolean result;
    Actions actions;

    //url which need to be tested
    String url = "https://todomvc.com/examples/angular2/";

    //chrome path in local
    String chromePath = "C:\\Users\\jains\\IdeaProjects\\ToDoTest\\driver\\chromedriver.exe";

    //data need to be tested
    String toDoItemOne = "Todo item one";
    String toDoEdit = "Todo item one edited";
    String todoSpace = "             ";

    //to launch the browser
    public void LaunchBrowser() {
        System.setProperty("webdriver.chrome.driver",chromePath);
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

     //open todos app url
        driver.navigate().to(url);
        web = new WebElements(driver);
    }
     //to verify all the elements in todos page
    public void verifyElementsAndAdd()  {
        driver.findElement(By.xpath(web.headingToDo_Xpath));
        driver.findElement(By.xpath(web.editField_Xpath));

        //add and verify the items entered by user
        addItemsInTodo();
        driver.quit();
    }

    //this method is created to add the items in todos list
    public void addItemsInTodo()  {
        //find the edit field to enter the items
       addField = driver.findElement(By.xpath(web.editField_Xpath));

       //adding item in totos field
       addField.sendKeys(toDoItemOne);

       //press enter
       addField.sendKeys(Keys.ENTER);

        //verify added item
       driver.findElement(By.xpath(web.addedItemOne_Xpath));

       //verify checkbox is available
       driver.findElement(By.xpath(web.todoCheckbox_Xpath));

       //verify the count field is available
       driver.findElement(By.xpath(web.todoCountField_Xpath));

       //verify close/cancel/delete button for the item is available
       driver.findElement(By.xpath(web.todoCloseButton_Xpath));

       //get the count of added items
       String count = driver.findElement(By.xpath(web.todoCount_Xpath)).getText();

       //check whether the count is 1 or not
       Assert.assertEquals(Integer.parseInt(count), 1);
       System.out.println("Item added successfully");
    }
    public void editItemInTodo() {
        //add the item in todos list
        addItemsInTodo();
        addedItem = driver.findElement(By.xpath(web.addedItemOne_Xpath));
        actions = new Actions(driver);

        //move to the checkbox field using action class and perform a double click on added item field to edit the same
        actions.moveToElement(addedItem).doubleClick().perform();
        WebElement edit = driver.findElement(By.xpath(web.edit_Xpath));

        //edit the existing added item
        edit.sendKeys(" edited");
        edit.sendKeys(Keys.ENTER);

        //get complete text of edited item
        String editedValue = driver.findElement(By.xpath(web.editedItemOne_Xpath)).getText();

        //verify the old and new value of current added item, new value should be displayed
        Assert.assertEquals(editedValue,toDoEdit);
        System.out.println("Item edited successfully");
        driver.quit();
    }
    public void deleteItemFromTodo() {
        //add the item in todos list
        addItemsInTodo();

        //verify the get the check box
        WebElement checkBox = driver.findElement(By.xpath(web.todoCheckbox_Xpath));

        //verify and get the delete/close button
        WebElement closeButton = driver.findElement(By.xpath(web.todoCloseButton_Xpath));
        actions = new Actions(driver);
        actions.moveToElement(checkBox).perform();

        //delete the added item from todos list
        closeButton.click();

        //verify that the item is deleted or not
        Assert.assertTrue(addField.isDisplayed());
        System.out.println("Item deleted successfully");
        driver.quit();
    }
    public void clearCompletedTodoTask() {
        //add the item in todos list
        addItemsInTodo();

        //verify the count field for added item
        driver.findElement(By.xpath(web.todoCountField_Xpath));

        //identify the checkbox
        WebElement checkBox = driver.findElement(By.xpath(web.todoCheckbox_Xpath));

        //click on checkbox
        checkBox.click();

        //verify the changed count in count field after clicking checkbox, should be reduced
        String count = driver.findElement(By.xpath(web.todoCount_Xpath)).getText();

        //verify that the count is reduced or not
        Assert.assertEquals(Integer.parseInt(count),0);

        //identify the clear button and click to clear the completed item
        WebElement clear = driver.findElement(By.xpath(web.clearCompleted_Xpath));
        clear.click();

        //verify that the cleared item is removed or not
        Assert.assertTrue(addField.isDisplayed());
        System.out.println("Task is completed successfully");
        driver.quit();
    }
    public void addSpaceItemInList()
    {
        //identify the field to add items
        addField = driver.findElement(By.xpath(web.editField_Xpath));

        //entering only spaces in add field
        addField.sendKeys(todoSpace);
        addField.sendKeys(Keys.ENTER);
        try {

            //verify that the item with only space is added or not
            driver.findElement(By.xpath(web.addedItemOne_Xpath));
            result = true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            result = false;
        }
       {
           //if the item tih only space is not added then test is passed else failed
          if (result == false)
              System.out.println("User is not able to add item with only spaces- Pass");
          else
              Assert.assertFalse(true, "This test is failed - User should not be able to add only spaces");
        }
        driver.quit();
    }
    public void clearField()
    {
        //identify add field to add the items
        addField = driver.findElement(By.xpath(web.editField_Xpath));

        //add an item
        addField.sendKeys(toDoItemOne);
        addField.sendKeys(Keys.ENTER);

        //identify the added item
        addedItem = driver.findElement(By.xpath(web.addedItemOne_Xpath));
        actions = new Actions(driver);

        //move to the added item and make it edit mode by double clicking
        actions.moveToElement(addedItem).doubleClick().perform();
        WebElement edit = driver.findElement(By.xpath(web.edit_Xpath));

        //clear the field and enter, empty item should not be added in the list
        edit.clear();

        //verify if empty item is added or not, if not then passed else failed
        WebElement addedEmptyItem = driver.findElement(By.xpath(web.addedItem_Xpath));
        Assert.assertFalse(addedEmptyItem.isDisplayed());
        driver.quit();
    }
    public void editOnCheckedFieldNotAllowed() {
        //add item to the list
        addItemsInTodo();

        //verify the count field
        driver.findElement(By.xpath(web.todoCountField_Xpath));

        //selecting the check box for added item
        WebElement checkBox = driver.findElement(By.xpath(web.todoCheckbox_Xpath));
        checkBox.click();
        addedItem = driver.findElement(By.xpath(web.addedItemOne_Xpath));
        actions = new Actions(driver);
        actions.moveToElement(addedItem).doubleClick().perform();

        //trying to edit the item which is added , checked and strike through, edit should not be allowed
        WebElement edit = driver.findElement(By.xpath(web.edit_Xpath));
        try {
            edit.sendKeys("edit");
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //if edit successful , the test case is failed
        Assert.assertFalse(result, "Test is failed - user should not be able to edit the checked field");
        driver.quit();
    }

    public void refreshBrowser() {
        //add item to the list
        addItemsInTodo();

        //refresh the page
        driver.navigate().to(url);

        //verify the previously added items are still available
        addedItem = driver.findElement(By.xpath(web.addedItemOne_Xpath));

        //if items are still available, this test case is failed
        Assert.assertFalse(addedItem.isDisplayed(), "All items should be cleared out on refresh");
        driver.quit();
    }
    public void duplicateItems() {
        //add item to the list
        addItemsInTodo();
        WebElement addField = driver.findElement(By.xpath(web.editField_Xpath));

        //add the same item again
        addField.sendKeys(toDoItemOne);
        addField.sendKeys(Keys.ENTER);

        //get first item
        String addedItemOne = driver.findElement(By.xpath(web.addedItemOne_Xpath)).getText();

        //get second item
        String addedItemTwo = driver.findElement(By.xpath(web.addedItemOneAgain_Xpath)).getText();

        //verify both of the added items are same, test case is failed
        //user should not allow to add the same item multiple times
        Assert.assertNotEquals(addedItemOne,addedItemTwo, "Test passed - User should not be able to add same item");
        driver.quit();
    }
    }


