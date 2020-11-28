package com.pinxv.hackathon2020_backend.selenium;

import cn.hutool.core.util.URLUtil;
import com.pinxv.hackathon2020_backend.vo.NewsVO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>description: </p>
 *
 * @date 2020/11/28
 */
public class NewsCrawler extends Crawler {

    private static String urlbase = "https://www.baidu.com/s?tn=news&word=";

    public static List<NewsVO> crawl(String keyword) {
        driver = new ChromeDriver();
        List<NewsVO> newsVOS = new ArrayList<>();
        String realURL = urlbase + URLUtil.encode(keyword);
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
