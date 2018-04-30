/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Arthur
 */
public class World 
{
    private int[][] tiles;
    private int worldX;
    private int worldY;
    private int screenX;
    private int screenY;
    
    private SpriteBatch batch;
    private Texture tilemap;
    
    public World()
    {
        tiles = new int[100][500];
        worldX = worldY = screenX = screenY = 0;
        tilemap = new Texture("tiles.png");
        batch = new SpriteBatch();
        Random r = new Random();
        for(int i = 0; i < 100; i++)
        {
            for(int j = 0; j < 500; j++)
            {
                tiles[i][j] = r.nextInt(76);
            }
        }
    }
    
    public void draw()
    {
        int currX = 0;
        int currY = 0;
        boolean firstLine = true;
        boolean firstCol = true;
        
        batch.begin();
        for(int i = 0; i < 15; i++)
        {
            firstCol = true;
            for(int j = 0; j < 16; j++)
            {
                int tileY = ((int) tiles[worldY + i][worldX + j] / 8);
                int tileX = ((int) tiles[worldY + i][worldX + j] % 10);
                TextureRegion tile = new TextureRegion(tilemap, tileX * 16, tileY * 16, 16, 16);
                
                int tileSizeY = 16;
                int tileSizeX = 16;
                
                if(firstLine)
                {
                    tileSizeY = 16 - screenY;
                    currY = tileSizeY;
                }
                if(firstCol)
                {
                    tileSizeX = 16 - screenX;
                }
                
                batch.draw( tile, currX, currY);
                currX += tileSizeX;
                
                firstCol = false;
            }
            firstLine = false;
            currX = 0;
            currY += 16;
        }
        batch.end();
    }
    
    public void move(int pixX, int pixY)
    {
        worldX += (screenX + pixX) / 16;
        screenX = (screenX + pixX) % 16;
        
        worldY += (screenY + pixY) / 16;
        screenY = (screenY + pixY) % 16;
        
        if(worldX >= 500 - 16)
        {
            worldX = 500 - 16;
        }
        
        if(worldY >= 100 - 15)
        {
            worldX = 100 - 15;
        }
    }
}
