package org.example.xpractice02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WP_Gauge4 {
        public static void main(String[] args) throws InterruptedException {
            WebDriverManager.edgedriver().setup();
            WebDriver driver = new EdgeDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();

            // Navigate to the webpage with data you want to scrape
            driver.get("https://app.blogvault.net/app/sites/overview");

            driver.findElement(By.xpath("//input[@name='user[email]']")).sendKeys("info@codingkart.com");
            driver.findElement(By.xpath("//input[@name='user[password]']")).sendKeys("YNY52d37lj@mtxmFP");
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            driver.findElement(By.xpath("//div[@id='mySidenav']/ul/li[2]/a/i/span")).click();
            Thread.sleep(2000);
          //  driver.findElement(By.xpath("//div[@class='react-select2__control css-k9ix3-control']")).click();
           //   Thread.sleep(2000);
          //  driver.findElement(By.xpath("//div[@id='react-select-5-option-1']")).click();
           // Thread.sleep(3000);
            driver.findElement(By.xpath("//div[@id='app']/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div/div/div[1]/div/div/a/img")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id='app']/div/div[2]/div[2]/section/div/div/div/div/div[2]/div[2]/div/div[1]/div/div[3]/div/div/a")).click();
            Thread.sleep(3000);
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("ScrapedData123");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("project_url");
            headerRow.createCell(1).setCellValue("total_attacks_blocked");
            headerRow.createCell(2).setCellValue("server_load_reduced");
            headerRow.createCell(3).setCellValue("total_file_scanned");


            for (int i = 0; i < 6; i++) {
                WebElement url = driver.findElement(By.xpath("//div[@id='app']/div/div[2]/div[2]/section/div/div/div/div/div[1]/div/div[1]/p[1]/span[2]/a/span"));
                String scrapedData = url.getText();

                Thread.sleep(2000);
                System.out.println("project_url : " + scrapedData);

                Row urlRow = sheet.createRow(i + 1);
                Cell cell1 = urlRow.createCell(0);
                cell1.setCellValue(scrapedData);

                Thread.sleep(3000);
                WebElement totalAttacks = driver.findElement(By.xpath("//div[@id=\"app\"]/div/div[2]/div[2]/div/div/div/div[2]/div/div[1]/div[1]"));
                String scrapedData1 = totalAttacks.getText();
                Row totalAttacksRow1 = sheet.getRow(i + 1);
                if (totalAttacksRow1 == null) {
                    totalAttacksRow1 = sheet.createRow(i + 1);
                }
                Cell cell2 = totalAttacksRow1.createCell(1);
                cell2.setCellValue(scrapedData1);
                System.out.println("totalAttacks: " + scrapedData1);
            }
            try (FileOutputStream fos = new FileOutputStream("C:\\Users\\Netizens\\IdeaProjects\\selenium-b14-project\\scraped_data8.xlsx")) {
                workbook.write(fos);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // driver.quit();
        }
    }


