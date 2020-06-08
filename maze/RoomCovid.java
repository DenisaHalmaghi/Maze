/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.awt.Color;

/**
 *
 * @author DENISA
 */
public class RoomCovid extends Room{
    
    public RoomCovid(int num) {
        super(num);
    }
     @Override
     //suprascriem culoarea
    public Color getColor() {
        return Color.RED;
    }
}
