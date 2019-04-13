package render;

import java.util.ArrayList;
import java.util.Comparator;

public class RenderableHolder {

	final private static RenderableHolder instance = new RenderableHolder();

	private ArrayList<IRenderable> entities;
	private Comparator<IRenderable> comparator;

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable a, IRenderable b) -> {
			if (a.getLayer() > b.getLayer()) {
				return 1;
			}
			return -1;
		};
	}

	public void add(IRenderable o) {
		entities.add(o);
		entities.sort(comparator);
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}

	public RenderableHolder getInstance() {
		return instance;
	}

	public ArrayList<IRenderable> getRenderableHolder() {
		return entities;
	}

}
