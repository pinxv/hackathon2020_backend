package com.pinxv.hackathon2020_backend.selenium;

import com.pinxv.hackathon2020_backend.enums.RiskLevelCode;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

    public static List<Pair<String, Integer>> crawl() {
        res.clear();
        driver = new ChromeDriver();
        driver.get("http://bmfw.www.gov.cn/yqfxdjcx/index.html");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            driver.close();
            return crawl();
        }
        List<WebElement> provinces = driver.findElements(By.xpath("//ul[@class='province']/li"));
        List<WebElement> cities;
        List<WebElement> blocks;
        if (provinces.isEmpty()) {
            driver.close();
            return crawl();
        }
        int i;
        int j = 0;
        for (i = 0; i < provinces.size() - 3; i++) {
            try {
                provinces = driver.findElements(By.xpath("//ul[@class='province']/li"));
                WebElement province = provinces.get(i);
                province.click();
                Thread.sleep(500);
                cities = driver.findElements(By.xpath("//ul[@class='city']/li"));
                for (j = 0; j < cities.size(); j++) {
                    if (i == 18 && j == 18) {
                        continue;
                    }
                    cities = driver.findElements(By.xpath("//ul[@class='city']/li"));
                    WebElement city = cities.get(j);
                    city.click();
                    Thread.sleep(500);
                    blocks = driver.findElements(By.xpath("//ul[@class='block']/li"));
                    for (int k = 0; k < blocks.size(); k++) {
                        blocks = driver.findElements(By.xpath("//ul[@class='block']/li"));
                        WebElement block = blocks.get(k);
                        block.click();
                        Thread.sleep(500);
                        if (driver.findElements(By.xpath("//div[@class='search-content']")).isEmpty()) {
                            List<WebElement> highRiskAreas = driver.findElements(By.xpath("//div[@class='risk-table']/tbody/tr/td"));
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
            } catch (ElementClickInterceptedException | InterruptedException e) {
                driver.close();
                return crawl(i, j);
            }
        }
        driver.close();
        return res;
    }

    private static List<Pair<String, Integer>> crawl(int i, int j) {
        driver = new ChromeDriver();
        driver.get("http://bmfw.www.gov.cn/yqfxdjcx/index.html");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            driver.close();
            return crawl(i, j);
        }
        List<WebElement> provinces = driver.findElements(By.xpath("//ul[@class='province']/li"));
        List<WebElement> cities;
        List<WebElement> blocks;
        if (provinces.isEmpty()) {
            driver.close();
            return crawl(i, j);
        }
        int ii;
        int jj = j;
        for (ii = i; ii < provinces.size() - 3; ii++) {
            try {
                provinces = driver.findElements(By.xpath("//ul[@class='province']/li"));
                WebElement province = provinces.get(ii);
                province.click();
                Thread.sleep(500);
                cities = driver.findElements(By.xpath("//ul[@class='city']/li"));
                for (jj = j; jj < cities.size(); jj++) {
                    if (ii == 18 && jj == 18) {
                        continue;
                    }
                    cities = driver.findElements(By.xpath("//ul[@class='city']/li"));
                    WebElement city = cities.get(jj);
                    city.click();
                    Thread.sleep(500);
                    blocks = driver.findElements(By.xpath("//ul[@class='block']/li"));
                    for (int k = 0; k < blocks.size(); k++) {
                        blocks = driver.findElements(By.xpath("//ul[@class='block']/li"));
                        WebElement block = blocks.get(k);
                        block.click();
                        Thread.sleep(500);
                        if (driver.findElements(By.xpath("//div[@class='search-content']")).isEmpty()) {
                            List<WebElement> highRiskAreas = driver.findElements(By.xpath("//table[@class='risk-table']/tbody/tr/td"));
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
            } catch (ElementClickInterceptedException | InterruptedException e) {
                driver.close();
                return crawl(ii, jj);
            }
        }
        driver.close();
        return res;
    }

}
