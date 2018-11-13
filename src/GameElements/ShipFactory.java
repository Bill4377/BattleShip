/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameElements;

/**
 * Ship Factory class. Implements Factory Design Pattern.
 *
 * @author Bill
 */
public class ShipFactory {

    /**
     * returns the specific type of ship we ask for.
     *
     * @param type
     * @return Ship Type.
     */
    public Ship getShip(String type) {

        if (type == null) {
            return null;
        }

        if (type.equals("ship3")) {
            return new Ship3();
        } else if (type.equals("ship4")) {
            return new Ship4();
        } else if (type.equals("ship5")) {
            return new Ship5();
        }

        return null;
    }

}
