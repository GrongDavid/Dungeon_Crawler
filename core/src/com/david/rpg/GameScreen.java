package com.david.rpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import helper.src.BodyHelper;
import helper.src.TileMapHelper;
import objects.player.Entity;
import objects.player.Player;

import static helper.src.Constants.PPM;

public class GameScreen implements Screen {
	final Drop game;
	final World world;

	private Player player;

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private final Vector3 touchPos = new Vector3();
	private Array<Rectangle> raindrops;

	private OrthogonalTiledMapRenderer renderer;
	private TileMapHelper tiledMapHelper;

	private BitmapFont scoreImg;
	private Texture playerImg;

	private Array<Body> bodies;

	public int viewportWidth = 30;
	public int viewportHeight = 30;


	public GameScreen(final Drop game) {
		this.game = game;
		this.world = new World(new Vector2(0, 0), true);
		this.bodies = new Array<Body>();
//		playerImg = new Texture(Gdx.files.internal("sprites/player/wizard-sprite-test.png"));
//
//		Body playerBody = BodyHelper.createBody(5, 5, playerImg.getWidth(), playerImg.getHeight(), false, world);
//
//		player = new Player(playerImg.getWidth(), playerImg.getHeight(), playerBody , playerImg);

		camera = new OrthographicCamera();
		camera.setToOrtho(false,viewportWidth,viewportHeight);

		batch = new SpriteBatch();

		this.tiledMapHelper = new TileMapHelper();
	}

	public void update(){
		world.step(1/60f, 0, 2);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		renderer.setView(camera);
	}

	public World getWorld(){
		return world;
	}

	@Override
	public void render (float delta) {// clear the screen with a dark blue color. The
		// arguments to clear are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		renderer = tiledMapHelper.setupMap();
		renderer.render();

		world.getBodies(bodies);

//		for(Body body : bodies){
//			Entity e = (Entity) body.getUserData();
//
//			if(e != null){
//				e.
//			}
//		}

		// tell the camera to update its matrices.

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.

		// begin a new batch and draw the bucket and
		// all drops
		game.batch.begin();
//		player.render(game.batch);
		game.batch.end();

		// process user input
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
//			bucket.x = touchPos.x - 64 / 2;
		}


		// make sure the bucket stays within the screen bounds
//		if (bucket.x < 0)
//			bucket.x = 0;
//		if (bucket.x > 800 - 64)
//			bucket.x = 800 - 64;

//		update();
//		player.update();

		Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
		debugRenderer.render(world, camera.combined.scl(PPM));
	}

	public void setPlayer(Player player){
		this.player = player;
	}

	@Override
	public void show() {

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

	@Override
	public void hide() {

	}

	@Override
	public void dispose () {

		scoreImg.dispose();
		batch.dispose();
	}
}
