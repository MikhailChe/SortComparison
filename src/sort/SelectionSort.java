package sort;

import java.util.Vector;

public class SelectionSort implements Runnable {

	public SelectionSort(Main themain) {
		this.main = themain;
	}

	public SelectionSort(Main themain, VecaRect vect) {
		this.main = themain;
		this.veca = vect;
		this.v = vect.Numbers;
	}

	public synchronized void Sort(Vector<Integer> vec) {
		int n = vec.size();
		for (int lh = 0; lh < n; lh++) {
			veca.DeselectAll();
			if (Main.stopAll == true) {
				return;
			}
			veca.SelectElement(lh);
			int rh = lh;
			for (int i = lh + 1; i < n; i++) {
				veca.DeselectAll();
				if (Main.stopAll == true) {
					return;
				}
				veca.SelectElement(lh);
				veca.SelectElement(i);
				veca.SelectElement(rh);
				main.pause(Main.PAUSETIME.getValue());
				if (vec.get(i) < vec.get(rh))
					rh = i;
			}
			Swap(vec, lh, rh);
		}
	}

	private synchronized void Swap(Vector<Integer> vect, int index1, int index2) {
		if (Main.stopAll == true) {
			return;
		}
		if (vect.size() > 200) {
			veca.Swap(index1, index2, false);
		} else {
			veca.Swap(index1, index2, true);
		}
	}

	@Override
	public void run() {
		veca.setName(SortName);
		/*Timer t = new Timer(true);
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				main.redrawG();
			}
		}, (long) 1, (long) 1);*/
		veca.clearTime();
		double thetime = System.currentTimeMillis();
		Sort(v);
		veca.setTime("" + (System.currentTimeMillis() - thetime) / 1000);
		veca.DeselectAll();
		main.redrawG();
		//t.cancel();
	}

	Main main;
	public VecaRect veca;
	public String SortName = "Сортировка выборкой";
	public Vector<Integer> v;

}
