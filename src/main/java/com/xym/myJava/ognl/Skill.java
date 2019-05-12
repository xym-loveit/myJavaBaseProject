package com.xym.myJava.ognl;

import java.util.List;
import java.util.Map;

/**
 * 技能
 *
 * @author xym
 * @create 2019-05-12 16:11
 */
public class Skill {
    private Map<String, List<SkillDetail>> maps;
    private String desc;

    public Map<String, List<SkillDetail>> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, List<SkillDetail>> maps) {
        this.maps = maps;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
