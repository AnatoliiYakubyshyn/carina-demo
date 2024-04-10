package com.zebrunner.carina.demo.browsermob;

import java.util.List;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.proxy.CaptureType;
import com.browserup.harreader.model.HarEntry;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.proxy.ProxyPool;
import com.zebrunner.carina.proxy.browserup.CarinaBrowserUpProxy;
import com.zebrunner.carina.utils.R;

public class BrowserMobTest extends AbstractTest {

    @Test
    public void test() {
        String url = "https://www.solvd.com/";
        String trackUrl = "https://px.ads.linkedin.com/wa/";
        R.CONFIG.put("browserup_proxy", "true", true);
        R.CONFIG.put("proxy_type", "DYNAMIC", true);
        R.CONFIG.put("proxy_port", "0", true);
        WebDriver driver = getDriver();
        BrowserUpProxy proxy = ProxyPool.getOriginal(CarinaBrowserUpProxy.class)
                .orElseThrow()
                .getProxy();

        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        getDriver().get(url);
        pause(100);
        proxy.newHar();
        List<HarEntry> entriesList = proxy.getHar().getLog().getEntries();
        System.out.println(entriesList.size());
        System.out.println("HELLO");
        entriesList.stream().forEach(el-> System.out.println(el.getRequest().getUrl()));
        Assert.assertTrue(entriesList.stream().anyMatch(el->el.getRequest().getUrl().contains(trackUrl)));

    }
}
