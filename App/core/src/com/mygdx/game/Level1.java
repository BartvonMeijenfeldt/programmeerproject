package com.mygdx.game;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Level1 implements ApplicationListener {
    private Texture ballImage;
    private Rectangle ball;
    private Sound ballLaunchedSound;
    private Music backGroundMusic;
    private OrthographicCamera camera;
    private SpriteBatch batch;



    @Override
    public void create() {
        ballImage = new Texture(Gdx.files.internal("ball.png"));

        /* ooit muziek
        ballLaunchedSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        backGroundMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        backGroundMusic.setLooping(true);
        backGroundMusic.play();
        */

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float ballSize = screenWidth / 10;

        camera = new OrthographicCamera(screenWidth, screenHeight);
        camera.update();
        //camera.setToOrtho(false, 200, 500);
        batch = new SpriteBatch();

        ball = new Rectangle();
        ball.x = screenWidth / 6 - ballSize / 2;
        ball.y = screenWidth / 6 - ballSize / 2;
        ball.width = ballSize;
        ball.height = ballSize;

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(ballImage, ball.x, ball.y);
        batch.end();

        if(Gdx.input.isTouched()) {
            // mogelijk is altijd ininiteëeren slecht, daarom dit verwijderen als app sloom gaat
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            ball.x = touchPos.x - 16 / 2;
            ball.y = touchPos.y - 16 / 2;
        }
    }

    @Override
    public void dispose() {
        ballImage.dispose();
        ballLaunchedSound.dispose();
        backGroundMusic.dispose();
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}

