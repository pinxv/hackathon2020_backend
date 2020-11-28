package com.pinxv.hackathon2020_backend.selenium;

import com.pinxv.hackathon2020_backend.enums.RiskLevelCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author njuselhx
 */
public class RiskLevelCrawler extends Crawler {

    private RiskLevelCrawler() {
        super();
    }

    private static final List<Pair<String, Integer>> res = new ArrayList<>();

    public static List<Pair<String, Integer>> crawl() throws InterruptedException {
        res.clear();
        DRIVER.get("http://bmfw.www.gov.cn/yqfxdjcx/index.html");
        Thread.sleep(2000);
        List<WebElement> provinces = DRIVER.findElements(By.xpath("//ul[@class='province']/li"));
        List<WebElement> cities;
        List<WebElement> blocks;
        if (provinces.isEmpty()) {
            crawl();
        }
        for (int i = 0; i < provinces.size() - 3; i++) {
            provinces = DRIVER.findElements(By.xpath("//ul[@class='province']/li"));
            WebElement province = provinces.get(i);
            province.click();
            Thread.sleep(300);
            cities = DRIVER.findElements(By.xpath("//ul[@class='city']/li"));
            for (int j = 0; j < cities.size(); j++) {
                if (i == 18 && j == 18) {
                    continue;
                }
                cities = DRIVER.findElements(By.xpath("//ul[@class='city']/li"));
                WebElement city = cities.get(j);
                city.click();
                Thread.sleep(300);
                blocks = DRIVER.findElements(By.xpath("//ul[@class='block']/li"));
                for (int k = 0; k < blocks.size(); k++) {
                    blocks = DRIVER.findElements(By.xpath("//ul[@class='block']/li"));
                    WebElement block = blocks.get(k);
                    block.click();
                    Thread.sleep(300);
                    if (!DRIVER.findElements(By.xpath("//div[@class='search-content']")).isEmpty()) {
                        // TODO update database
                    } else {
                        // TODO traverse table
                        List<WebElement> highRiskAreas = DRIVER.findElements(By.xpath("//div[@class='risk-table']/tbody/tr/td"));
                        for (int highRiskIter = 0; highRiskIter < highRiskAreas.size(); highRiskIter += 2) {
                            String areaName = highRiskAreas.get(highRiskIter).getText();
                            String riskLevel = highRiskAreas.get(highRiskIter + 1).getText();
                            Integer risk = RiskLevelCode.LOW.getCode();
                            if (riskLevel.contains("高")) {
                                risk = RiskLevelCode.HIGH.getCode();
                            } else if (riskLevel.contains("中")) {
                                risk = RiskLevelCode.MEDIUM.getCode();
                            }
                            String locationName = province.getText() + city.getText() + block.getText() + areaName;
                            res.add(Pair.of(locationName, risk));
                        }
                    }
                }
            }
        }
        DRIVER.close();
        return res;
    }

}
