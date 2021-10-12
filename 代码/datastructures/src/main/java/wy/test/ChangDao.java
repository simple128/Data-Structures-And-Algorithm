//package wy.test;
//
//import com.alibaba.excel.EasyExcel;
//import com.alibaba.excel.context.AnalysisContext;
//import com.alibaba.excel.event.AnalysisEventListener;
//import lombok.SneakyThrows;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import java.util.Objects;
//
///**
// * @description:
// * @author: ohy
// * @create: 2021-01-09 17:38:47
// **/
//public class ChangDao {
//
//    @Test
//    public void addTask() throws InterruptedException {
//        String realName = "欧韩勇";
//        String userName = "ouhy";
//        String password = "ohy5201314";
//        String googleDriver = "E:\\chromedriver.exe";
//        String url = "http://192.169.2.79/zentao";
//        String fileName = "C:\\Users\\montnets\\Desktop\\aim系统软件功能列表v1.1-细分任务.xls";
//        System.setProperty("webdriver.chrome.driver", googleDriver);
//        WebDriver driver = new ChromeDriver();
//        driver.get(url);
//        driver.findElement(By.id("account")).sendKeys(userName);
//        driver.findElement(By.name("password")).sendKeys(password);
//        driver.findElement(By.id("submit")).click();
//        Thread.sleep(1500);
//        driver.manage().window().maximize();
//        driver.findElement(By.id("navbar")).findElement(By.cssSelector("li[data-id=project]")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.id("subNavbar")).findElement(By.cssSelector("li[data-id=list]")).click();
//        driver.findElement(By.id("currentItem")).click();
//
//        driver.findElement(By.id("dropMenu")).findElement(By.cssSelector("input[type=search]")).sendKeys("AIM云平台V1.1");
//        Thread.sleep(1500);
//        driver.findElement(By.id("dropMenu")).findElement(By.cssSelector("input[type=search]")).sendKeys(Keys.ENTER);
//        Thread.sleep(1500);
//
//
//        EasyExcel.read(fileName, Task.class, new DemoDataListener(driver, realName)).sheet(3).doRead();
//        driver.close();
//
//    }
//
//    public void addTask(WebDriver driver, String realName, String module, String demand, String startTime, String endTime, String manHour, String taskName) throws InterruptedException {
//        Thread.sleep(2000);
//        driver.get("http://192.169.2.79/zentao/project-task-1223.html");
//        driver.findElement(By.id("mainMenu")).findElement(By.className("pull-right")).findElement(By.className("btn-primary")).click();
//        Thread.sleep(1500);
//        //开发
//        driver.findElement(By.id("type_chosen")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.id("type_chosen")).findElement(By.className("chosen-results")).findElement(By.cssSelector("li[title=开发]")).click();
//        Thread.sleep(1500);
//        //指派人
//        driver.findElement(By.id("dataPlanGroup")).click();
//        Thread.sleep(1500);
//
//        driver.findElement(By.id("assignedTo_chosen")).findElement(By.className("chosen-search")).findElement(By.tagName("input")).sendKeys(realName);
//        Thread.sleep(1500);
//        driver.findElement(By.id("assignedTo_chosen")).findElement(By.className("chosen-search")).findElement(By.tagName("input")).sendKeys(Keys.ENTER);
//        //任务名称
//        driver.findElement(By.id("name")).sendKeys(taskName);
//        //预计耗时
//        driver.findElement(By.id("estimate")).sendKeys(manHour);
//        startTime = startTime.replace("年", "-").replace("月", "-").replace("日", "");
//        endTime = endTime.replace("年", "-").replace("月", "-").replace("日", "");
//        //开始时间
//        driver.findElement(By.id("estStarted")).sendKeys(startTime);
//        //结束时间
//        driver.findElement(By.id("deadline")).sendKeys(endTime);
//        //所属模块
//        driver.findElement(By.id("module_chosen")).click();
//        Thread.sleep(1500);
//        module = module.substring(module.indexOf("/"));
//        module = module.substring(0, module.lastIndexOf("/"));
//        module = module.substring(0, module.lastIndexOf("/"));
//        driver.findElement(By.id("module_chosen")).findElement(By.className("chosen-search")).findElement(By.tagName("input")).sendKeys(module);
//        Thread.sleep(1500);
//        driver.findElement(By.id("module_chosen")).findElement(By.className("chosen-search")).findElement(By.tagName("input")).sendKeys(Keys.ENTER);
//        Thread.sleep(1500);
//        //相关需求
//        driver.findElement(By.id("story_chosen")).click();
//        Thread.sleep(1500);
//        driver.findElement(By.id("story_chosen")).findElement(By.className("chosen-search")).findElement(By.tagName("input")).sendKeys(demand);
//        Thread.sleep(1500);
//        driver.findElement(By.id("story_chosen")).findElement(By.className("chosen-search")).findElement(By.tagName("input")).sendKeys(Keys.ENTER);
//        Thread.sleep(1500);
//        driver.findElement(By.id("aftertoTaskList")).click();
//        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
//        //保存
//        driver.findElement(By.className("form-actions")).findElement(By.className("btn-wide")).click();
//        // driver.findElement(By.id("submit")).click();
//    }
//}
//
//class DemoDataListener extends AnalysisEventListener<Task> {
//
//    WebDriver driver;
//    String realName;
//
//    public DemoDataListener(WebDriver driver, String realName) {
//        this.driver = driver;
//        this.realName = realName;
//    }
//
//
//    @SneakyThrows
//    @Override
//    public void invoke(Task data, AnalysisContext context) {
//        if (Objects.equals(data.getUserName(), realName)) {
//            ChangDao aaa = new ChangDao();
//            aaa.addTask(driver, realName, data.getModule(), data.getModule(), data.getEndTime(), data.getEndTime(), data.getManHour(), data.getTaskName());
//        }
//    }
//
//    @Override
//    public void doAfterAllAnalysed(AnalysisContext context) {
//    }
//}
//
