/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Admin
 */
public class SubImage {

    private int subImageID;
    private int pID;
    private String image;

    public SubImage() {
    }

    public SubImage(int subImageID, int pID, String image) {
        this.subImageID = subImageID;
        this.pID = pID;
        this.image = image;
    }

    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSubImageID() {
        return subImageID;
    }

    public void setSubImageID(int subImageID) {
        this.subImageID = subImageID;
    }

    @Override
    public String toString() {
        return "SubImage{" + "pID=" + pID + ", image=" + image + '}';
    }

}
