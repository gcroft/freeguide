/*
 * FreeGuide
 *
 * Copyright (c) 2001 by Andy Balaam
 *
 * Released under the GNU General Public License
 * with ABSOLUTELY NO WARRANTY.
 *
 * See the file COPYING for more information.
 */

import java.util.Calendar;

/**
 * FreeGuideProgramme
 *
 * A class that holds info about a particular programme.
 *
 * @author Andy Balaam
 * @version 2
 */

public class FreeGuideProgramme {
    
    public FreeGuideProgramme() {
    }

    public void setStart(Calendar start) {this.start=start;}
    public Calendar getStart() {return start;}
    
    public void setEnd(Calendar end) {this.end=end;}
    public Calendar getEnd() {return end;}
    
    public void setTitle(String title) {this.title=title;}
    public String getTitle() {return title;}
    
    public void setDesc(String desc) {this.desc=desc;}
    public String getDesc() {return desc;}
    
    public void setChannelName(String channelName) {this.channelName=channelName;}
    public String getChannelName() {return channelName;}
    
	public void setChannelID(String channelID) {this.channelID=channelID;}
	public String getChannelID() {return channelID;}
	
    public void setCategory(String category) {this.category=category;}
    public String getCategory() {return category;}
    
	/**
	 * Decides whether two programme objects refer to the same programme
	 *
	 * Programmes are assumed to be uniquely identified by their title, start
	 * time and channel.
	 */
	public boolean equals(Object obj) {
		
		if(obj == null) {
			return false;
		}
		
		if(!(obj instanceof FreeGuideProgramme) ) {
			return false;
		}
		
		FreeGuideProgramme other = (FreeGuideProgramme)obj;
		
		if(title.equals(other.getTitle()) &&
			start.equals(other.getStart()) &&
			channelID.equals(other.getChannelID())) {
		
			return true;
				
		}
		
		return false;
		
	}
	
	/**
	 * Returns a hashcode for the programme.
	 *
	 * Implemented to keep consistency since the equals methid was overridden.
	 */
	public int hashCode() {
		
		// Just add up 3 values - stupid?
		byte[] titleBytes = title.getBytes();
		long startMS = start.getTimeInMillis();
		byte[] channelBytes = channelID.getBytes();
		
		int ans=0;
		for(int i=0;i<titleBytes.length;i++) {
			ans += titleBytes[i];
		}
		ans += (int)(startMS / (1000*60));	// Time in minutes since 1970
		for(int i=0;i<channelBytes.length;i++) {
			ans += channelBytes[i];
		}
		
		return ans;
		
	}
	
    private Calendar start;		// The start time
    private Calendar end;		// The end time
    private String title;	// The programme title
    private String desc;	// The programme description
    private String channelName;	// The name of the channel the prog's on
	private String channelID;	// The ID of the channel the prog's on
    private String category;	// The category it fits into
    
}
