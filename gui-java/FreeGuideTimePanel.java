/*
 * FreeGuide J2
 *
 * Copyright (c) 2001 by Andy Balaam
 *
 * Released under the GNU General Public License
 * with ABSOLUTELY NO WARRANTY.
 *
 * See the file COPYING for more information.
 */


import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;


/**
 * A panel that displays a ruler-like time line.
 *
 * @author  Andy Balaam
 * @version 2
 */
public class FreeGuideTimePanel extends javax.swing.JPanel {
	
    public FreeGuideTimePanel() {
		
        initComponents();
		
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
	private void initComponents() {//GEN-BEGIN:initComponents
		
		setLayout(new java.awt.BorderLayout());
		
	}//GEN-END:initComponents

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
			
		int wid = this.getPreferredSize().width;
		
		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
		
		if(wid>0) {
		
			multiplier = (double)(endTime.getTime() - startTime.getTime()) / (double)(wid);
		
			Date tmpTime = new Date(startTime.getTime());
		
			// Forget about seconds
			tmpTime.setSeconds(0);
		
			// Round to the nearest 5 mins
			tmpTime.setMinutes((((int)(tmpTime.getMinutes()/5)))*5);
		
			// Step through each 5 mins
			while(tmpTime.before(endTime)) {
			
				int xPos = (int)((tmpTime.getTime() - startTime.getTime())/multiplier);
			
				// Make a mark
				
				if(tmpTime.getMinutes()==0) {	// Hours
					
					g.drawLine(xPos, 0, xPos, 10);
					g.drawString(fmt.format(tmpTime), xPos-17, 21);
					
				} else if(tmpTime.getMinutes()==30) {	// Half hours
					
					g.drawLine(xPos, 0, xPos, 7);
					g.drawString(fmt.format(tmpTime), xPos-17, 21);
					
				} else if((tmpTime.getMinutes()%10) == 0) {	// 10 mins
					
					g.drawLine(xPos, 0, xPos, 4);
					
				} else {
					
					g.drawLine(xPos, 0, xPos, 1);
					
				}
				
				
			
				// Add another 5 mins
				tmpTime.setMinutes(tmpTime.getMinutes()+5);
		
			}//while
			
			// Draw the "now" line
			int xPos = (int)((nowTime.getTime() - startTime.getTime())/multiplier);
			g.fillRect(xPos-1, 0, 3, 25);
			
		}//if
			
	}//paintComponent

	public int getNowScroll() {
		
		return (int)((nowTime.getTime() - startTime.getTime()) / multiplier);
		
	}
	
	/*public void setTimes(Date newStartTime, Date newEndTime, Date newNowTime, int newWidth) {
		
		setTimes(newStartTime, newEndTime, newNowTime);
		
	}*/
	
	public void setTimes(Date newStartTime, Date newEndTime, Date newNowTime) {
				
		startTime = new Date(newStartTime.getTime());
		endTime = new Date(newEndTime.getTime());
		nowTime = new Date(newNowTime.getTime());
		
		repaint();
		
	}
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables

	private Date startTime;	// The time on the left hand side of the panel
	private Date endTime;	// The time on the right hand side of the panel
	private Date nowTime;	// The time now
	private double multiplier;	// The no. millisecs over the no. pixels
	
	
}
