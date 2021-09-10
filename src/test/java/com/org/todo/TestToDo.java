package com.org.todo;

import com.org.todo.Util;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;

public class TestToDo extends Util {

    Util util;
    WebElement web;

    //Complete the setup before start running the test cases, where browser gets launched and web portal opens
//Added all te test cases with annotation test and provide the priorities to run the cases
//Below test suite(complete) will be running by testng.xml file
    @BeforeMethod
    public void InitialSetUp() {
        util = new Util();
        util.LaunchBrowser();
    }
    @Test(priority = 0)
    public void elements()  {
        util.verifyElementsAndAdd();
    }
    @Test (priority = 1)
    public void editToDo()  {
        util.editItemInTodo();
    }
    @Test (priority = 2)
    public void deleteToDo()  {
        util.deleteItemFromTodo();
    }
    @Test (priority = 3)
    public void clearCompleteToDo()  {
        util.clearCompletedTodoTask();
    }
    @Test (priority = 4)
    public void itemWithSpace()
    {
        util.addSpaceItemInList();
    }
    @Test (priority = 5)
    public void listBlockEmpty()
    {
        util.clearField();
    }
    @Test (priority = 6)
    public void editNotAllowed()  {
        util.editOnCheckedFieldNotAllowed();
    }
    @Test (priority = 8)
    public void clearOnRefreshBrowser()  {
        util.refreshBrowser();
    }
    @Test (priority = 7)
    public void duplicateItems()  {
        util.duplicateItems();
    }

}
