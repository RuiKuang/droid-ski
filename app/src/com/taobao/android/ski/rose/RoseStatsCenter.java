
package com.taobao.android.ski.rose;

import java.util.ArrayList;

import android.graphics.Rect;


public class RoseStatsCenter {
	
    private static final String TAG = "RoseStatsCenter";
    
    public static final int LEVEL_ERROR = 1;
    public static final int LEVEL_WARN = 2;
    public static final int LEVEL_VERBOSE = 3;
        
    public static class Stats {
        public int level;
        public String name;
        public int nameWidth;
        public String content;
        public int contentWidth;
        public int contentHeight;
    }
    
    private static RoseStatsCenter mRoseStatsCenter;
    private final ArrayList<Stats> mStats = new ArrayList<Stats>();
    private RoseStatsChange mOberver;

    public static synchronized RoseStatsCenter instance(){
    	if(mRoseStatsCenter == null){
    		mRoseStatsCenter = new RoseStatsCenter();
    	}
    	
    	return mRoseStatsCenter;
    }
    
    public void addStats(int level, String name, String content){
    	Stats stat = new Stats();
    	stat.level = level;
    	stat.name = name;
    	stat.content = content;

    	mStats.add(stat);
    	
    	mOberver.notifyDataSetChange();
    }
    
    public void setRoseStatsChange(RoseStatsChange roseStatsChange) {
    	this.mOberver = roseStatsChange;
    }
    
    public void init() {
        update();
    }
    
    public void update() {

    	if(mOberver != null) {

    		int maxWidth = 0;
    		int allHeight = 0;
	    	if(mStats.size() > 0){
	    		
	    		for(Stats stat : mStats) {
	    			
	    			Rect rectname = mOberver.getTextRect(stat.name);
	    			stat.nameWidth = rectname.width();
	    			
	    			
	    			
	    			Rect rectcont = mOberver.getTextRect(stat.content);
	    			stat.contentWidth = rectcont.width();
	    			stat.contentHeight = rectcont.height();
	    			
	    			 
	    		}

	    		mOberver.notifyDataSetChange();
        	}
    	}
    }
    
    
}
