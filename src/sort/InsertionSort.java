package sort;

import java.util.Vector;

public class InsertionSort extends AbstractSort {

	public InsertionSort(VecaRect vect) {
		super(vect);
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized void Sort(Vector<Integer> vect) {
		int i, j;
		for (i = 1; i < vect.size(); i++) {
			veca.DeselectAll();
			if (Main.stopAll == true) {
				/*
				 * Если переменная stopAll в объекте класса Main является true,
				 * то прекращаем сортировку
				 */
				return;
			}
			veca.SelectElement(i);
			Main.pause(Main.PAUSETIME.getValue());
			j = i;
			while (j > 0 && vect.get(j - 1) > vect.get(j)) {
				veca.SelectElement(i);
				veca.SelectElement(j);
				veca.SelectElement(j - 1);
				Main.pause(Main.PAUSETIME.getValue());
				Swap(vect, j, j - 1);
				veca.DeselectAll();
				if (Main.stopAll == true) {
					/*
					 * Если переменная stopAll в объекте класса Main является
					 * true, то прекращаем сортировку
					 */
					return;
				}
				j--;
			}
		}
	}

	@Override
	public synchronized void SetName() {
		veca.setName("Сортировка вставками");
	}
}
