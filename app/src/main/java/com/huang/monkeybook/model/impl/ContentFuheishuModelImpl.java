//Copyright (c) 2017. 章钦豪. All rights reserved.
package com.huang.monkeybook.model.impl;

import com.huang.monkeybook.model.IWebContentModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.TextNode;
import java.util.List;

/**
 * 站点有问题  未完全解析
 */
public class ContentFuheishuModelImpl implements IWebContentModel{
    public static final String TAG = "http://fuheishu.com";

    public static ContentFuheishuModelImpl getInstance(){
        return new ContentFuheishuModelImpl();
    }

    private ContentFuheishuModelImpl(){

    }
    @Override
    public String analyBookcontent(String s, String realUrl) throws Exception{
        Document doc = Jsoup.parse(s);
        String bookId = realUrl.substring(realUrl.indexOf("/",21)+1,realUrl.indexOf(".html"));
        List<TextNode> contentTNs = doc.getElementById("content"+bookId).textNodes();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<contentTNs.size();i++){
            String temp = contentTNs.get(i).text().trim();
            temp = temp.replaceAll(" ","").replaceAll(" ","");
            if (temp.length() > 0) {
                stringBuilder.append("\u3000\u3000" + temp);
                if (i < contentTNs.size() - 1) {
                    stringBuilder.append("\r\n");
                }
            }
        }
        return stringBuilder.toString();
    }
}