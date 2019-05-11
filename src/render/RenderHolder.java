package render;

import java.util.ArrayList;
import java.util.Comparator;

public class RenderHolder {

	private static final RenderHolder instance = new RenderHolder();
	
	private ArrayList<Renderable> allRender;
	private Comparator<Renderable> comparator;
	
	public RenderHolder() {
		allRender = new ArrayList<Renderable>();
		comparator = new Comparator<Renderable>() {
			@Override
			public int compare(Renderable arg0, Renderable arg1) {
				// TODO Auto-generated method stub
				if (arg0.getLayer() < arg1.getLayer()) {
					return -1;
				}
				return 0;
			}
		};
	}
	
	public void addNewObject(Renderable i) {
		allRender.add(i);
		allRender.sort(comparator);
	}
	
	public static RenderHolder getInstance() {
		return instance;
	}
	
	public void update() {
		for (int i = allRender.size()-1; i >= 0; i--) {
			if (!allRender.get(i).isAlive()) {
				allRender.remove(i);
			}
		}
	}
	
	public ArrayList<Renderable> getHolder() {
		return allRender;
	}
	
	public void reset() {
		allRender.clear();
	}
	
}
