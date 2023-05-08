/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author j_ped
 */
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;

public class RoundedBorder extends JPanel {
    private int roundTopLeft=0;
    int roundTopRight=0;
    int roundBottomLeft=0;
    int roundBottomRight=0;

    
    public RoundedBorder() {
       setOpaque(false);
    }
    
    public int getRoundTopLeft() {
        return roundTopLeft;
    }

    public void setRoundTopLeft(int roundTopLeft) {
        this.roundTopLeft = roundTopLeft;
        repaint();
    }

    public int getRoundTopRight() {
        return roundTopRight;
    }

    public void setRoundTopRight(int roundTopRight) {
        this.roundTopRight = roundTopRight;
        repaint();
    }

    public int getRoundBottomLeft() {
        return roundBottomLeft;
    }

    public void setRoundBottomLeft(int roundBottomLeft) {
        this.roundBottomLeft = roundBottomLeft;
        repaint();
    }

    public int getRoundBottomRight() {
        return roundBottomRight;
    }

    public void setRoundBottomRight(int roundBottomRight) {
        this.roundBottomRight = roundBottomRight;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics graphics){
        Graphics2D g2= (Graphics2D) graphics.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        Area area = new Area (createRoundTopLeft());
        if(roundTopRight>0){
            area.intersect(new Area (createRoundTopRight()));
        
        }
        if(roundTopLeft>0){
            area.intersect(new Area (createRoundTopRight()));
        
        }
        if(roundBottomLeft>0){
            area.intersect(new Area (createRoundbottomLeft()));
        
        }
        if(roundBottomRight>0){
            area.intersect(new Area (createRoundbottomRight()));
        
        }
      
        g2.fill(area);
        g2.dispose();
        super.paintComponent(graphics);
        
    
    }
    private Shape createRoundTopRight(){
        int width = getWidth();
        int height = getHeight();
        int roundX= Math.min(width, roundTopRight);
        int roundY = Math.min(height, roundTopRight);
        Area area =  new Area (new RoundRectangle2D.Double(0,0,width, height, roundX, roundY));
        area.add(new Area (new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height-roundY/2)));
        return area;
        
                
    
    }
      private Shape createRoundTopLeft(){
        int width = getWidth();
        int height = getHeight();
        int roundX= Math.min(width, roundTopLeft);
        int roundY = Math.min(height, roundTopLeft);
        Area area =  new Area (new RoundRectangle2D.Double(0,0,width, height, roundX, roundY));
        area.add(new Area (new Rectangle2D.Double(roundX/2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height-roundY/2)));
        return area;
        
                
    
    }
      private Shape createRoundbottomLeft(){
        int width = getWidth();
        int height = getHeight();
        int roundX= Math.min(width, roundBottomLeft);
        int roundY = Math.min(height, roundBottomLeft);
        Area area =  new Area (new RoundRectangle2D.Double(0,0,width, height, roundX, roundY));
        area.add(new Area (new Rectangle2D.Double(roundX/2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height-roundY/2)));
        return area;
        
    }
   
      private Shape createRoundbottomRight(){
        int width = getWidth();
        int height = getHeight();
        int roundX= Math.min(width, roundBottomRight);
        int roundY = Math.min(height, roundBottomRight);
        Area area =  new Area (new RoundRectangle2D.Double(0,0,width, height, roundX, roundY));
        area.add(new Area (new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height-roundY/2)));
        return area;
        
    }
   


  


}

