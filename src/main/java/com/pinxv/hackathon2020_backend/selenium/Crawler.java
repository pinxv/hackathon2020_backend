package com.pinxv.hackathon2020_backend.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author njuselhx
 */
public abstract class Crawler {

    protected Crawler() {
    }

    protected static final WebDriver DRIVER = new ChromeDriver();

}
