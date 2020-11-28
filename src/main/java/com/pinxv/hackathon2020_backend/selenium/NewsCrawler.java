package com.pinxv.hackathon2020_backend.selenium;

import cn.hutool.core.util.URLUtil;
import com.pinxv.hackathon2020_backend.vo.NewsVO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>description: </p>
 *
 * @date 2020/11/28
 */
public class NewsCrawler extends Crawler {

    private static String urlBase = "https://www.baidu.com/s?tn=news&word=";

    public static List<NewsVO> crawl(String keyword) {
        try {
            driver = new ChromeDriver();
        } catch (Exception e) {
            System.setProperty("webdriver.chrome.driver","/opt/chromedriver");
            ChromeOptions chromeOptions = new ChromeOptions();
            // 设置为 headless 模式 （无头浏览器）
            chromeOptions.addArguments("--headless","--no-sandbox","--disable-gpu","--disable-dev-shm-usage");
            driver = new ChromeDriver(chromeOptions);
        }
        List<NewsVO> newsVOS = new ArrayList<>();
        String realURL = urlBase + URLUtil.encode(keyword);
        driver.get(realURL);
        List<WebElement> newsDescription = driver.findElements(By.xpath("//div[@class='result-op c-container xpath-log new-pmd']//span[@class='c-font-normal c-color-text']"));
        List<WebElement> newsTitle = driver.findElements(By.xpath("//div[@class='result-op c-container xpath-log new-pmd']//h3//a"));
        for (int i = 0; i < newsDescription.size(); i++) {
            NewsVO newsVO = new NewsVO();
            newsVO.setTitle(newsTitle.get(i).getText());
            newsVO.setUrl(newsTitle.get(i).getAttribute("href"));
            newsVO.setDescription(newsDescription.get(i).getText());
            newsVOS.add(newsVO);
        }
        driver.close();
        return newsVOS;
    }

}
