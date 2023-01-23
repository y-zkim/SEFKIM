package com.insacvl.sefkim_flickr.model;
/**
* @Author : ZKIM Youssef
*/

/*
 *=================================================================================================*
 *                                  Developed by : ZKIM Youssef                                    *
 *=================================================================================================*
 *=================================================================================================*
 *                                                                                                 *
 *                                            Card                                                 *
 *                                                                                                 *
 *=================================================================================================*
 * Class Description                                                                               *
 * ----------------                                                                                *
 * Model for the cards generated at home screen.                                                   *
 *                                                                                                 *
 *=================================================================================================*

 */

public class Card {
    String id;
    String name;

    public Card(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
