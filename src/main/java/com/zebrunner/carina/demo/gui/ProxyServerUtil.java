package com.zebrunner.carina.demo.gui;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.proxy.CaptureType;
import com.browserup.harreader.model.HarEntry;
import com.zebrunner.carina.proxy.ProxyPool;
import com.zebrunner.carina.proxy.browserup.CarinaBrowserUpProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProxyServerUtil {

    private static final Logger LOGGER = LogManager.getLogger(ProxyServerUtil.class);
    private List<String> caughtContent = new ArrayList<>();
    private final BrowserUpProxy proxy;

    public ProxyServerUtil() {
        proxy = ProxyPool.getOriginal(CarinaBrowserUpProxy.class)
                .orElseThrow().getProxy();
    }

    public void setFilter(String key) {
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        proxy.newHar();
//        proxy.addRequestFilter((request, contents, messageInfo) -> {
//            if (messageInfo.getOriginalUrl().contains(key)) {
//                String decodedString = java.net.URLDecoder.decode(contents.getTextContents(), StandardCharsets.UTF_8);
//                caughtContent.add(decodedString);
//                LOGGER.info("Caught content: " + decodedString);
//            }
//            return null;
//        });
    }

    public List<HarEntry> getEntriesByRqPattern(final String requestUrlPattern) {
        return proxy.getHar().getLog().getEntries().stream().filter(
                        entry -> entry.getRequest().getUrl().contains(requestUrlPattern))
                .collect(Collectors.toList());
    }
}