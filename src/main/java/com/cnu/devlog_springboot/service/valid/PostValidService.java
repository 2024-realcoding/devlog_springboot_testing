package com.cnu.devlog_springboot.service.valid;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostValidService {
//    public boolean isSlangInclude(List<String> slangList,
//                                  String title,
//                                  String postContent) {
//        return false;
//    }

    public boolean isSlangInclude(List<String> slangList,
                               String title,
                               String postContent) {
        for (String slang : slangList) {
            if(title.contains(slang)
                    || postContent.contains(slang)) {
                return true;
            }
        }
        return false;
    }
}
