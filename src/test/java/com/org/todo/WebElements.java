package com.org.todo;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElements {

    private WebDriver driver;

    WebElements(WebDriver driver)
    {
        this.driver = driver;
    }
//This is to specify all the available web elements which need to be tested
    String headingToDo_Xpath = "//h1[text()='todos']";
    String editField_Xpath = "//input[@placeholder='What needs to be done?']";
    String edit_Xpath = "//*[@class='edit']";
    String addedItemOne_Xpath = "(//input[@class='toggle']/following-sibling::label[text()='Todo item one'])[1]";
    String addedItemOneAgain_Xpath = "(//input[@class='toggle']/following-sibling::label[text()='Todo item one'])[2]";
    String todoCountField_Xpath = "//span[@class='todo-count' or @text='items left']";
    String todoCount_Xpath = "//span[@class='todo-count']/strong";
    String todoCheckbox_Xpath = "//input[@class='toggle' and @type='checkbox']";
    String todoCloseButton_Xpath = "//input[@class='toggle']/following-sibling::button[@class='destroy']";
    String editedItemOne_Xpath = "//input[@class='toggle']/following-sibling::label[text()='Todo item one edited']";
    String clearCompleted_Xpath = "//button[text() ='Clear completed']";
    String addedItem_Xpath = "//input[@class='toggle']/following-sibling::label";

}
