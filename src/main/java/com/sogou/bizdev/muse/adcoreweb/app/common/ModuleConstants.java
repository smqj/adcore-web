package com.sogou.bizdev.muse.adcoreweb.app.common;

import java.text.SimpleDateFormat;

public class ModuleConstants {
	public static enum ChoiceEnum {
		//choice 0:findAll ,1:findById		
        FINDALL("列出所有"),
        FINDBYID("根据ID查询");
        
        String text;

        public String getText() {
            return text;
        }

        private ChoiceEnum(String text) {
            this.text = text;
        }

        public static ChoiceEnum parse(int i) {
        	if (i < ChoiceEnum.values().length&&i >= 0) {
        		return ChoiceEnum.values()[i];
			}
            return null;
        }
    }
	
	public static boolean isNum(String str){
		if (str != null) {
			return str.matches("[0-9]+");
		}
		return false;
	}
	
	public static SimpleDateFormat dateFormat1=new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat dateFormat2=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
}


