package sort;

import java.util.Vector;

public class BubbleSort implements Runnable {

	public BubbleSort(Main themain) {
		/*
		 * Конструктор сортировочного класса BubbleSort (сортировка пузырьком)
		 * Принимает в качестве параметра ссылку на объект класса Main
		 */
		this.main = themain;
	}

	public BubbleSort(Main themain, VecaRect vect) {
		/*
		 * Второй конструктор сортировочного класса BubbleSort В качестве
		 * параметра принимает ссылку на объект класса Main и массив, который
		 * нужно отсортировать
		 */
		this.main = themain;
		this.veca = vect;
		this.v = vect.Numbers;
		/*
		 * Присваиваем переменной main класса BubbleSort переданую переменную
		 * themain;
		 * 
		 * Присваиваем переменной veca переданный массив;
		 * 
		 * Присваиваем переменной v массив из значений;
		 */
	}

	public synchronized void Sort(Vector<Integer> vect) {
		/*
		 * Функция начала сортировки. В качетве аргумента принимает вектор из
		 * целых чисел.
		 * 
		 * Далее идет простой алгоритм пузырьковой сортировки.
		 */
		boolean swapped = false;
		do {
			swapped = false;
			for (int i = 1; i < vect.size(); i++) {
				veca.DeselectAll();
				/*
				 * Здесь снимаем графическое выделение со всех элементов
				 */
				if (Main.stopAll == true) {
					/*
					 * Если переменная stopAll в объекте класса Main является
					 * true, то прекращаем сортировку
					 */
					return;
				}
				veca.SelectElement(i);
				/*
				 * Создаем графическое выделение i элемента
				 */
				main.pause(Main.PAUSETIME.getValue());
				/*
				 * Делаем задержку на Main.PAUSETIME милисекунд
				 */
				if (vect.get(i - 1) > vect.get(i)) {
					veca.DeselectAll();
					/*
					 * Снимаем выделение.
					 */
					veca.SelectElement(i-1);
					veca.SelectElement(i);
					/*
					 * Ставим выделение на i элемент
					 */
					Swap(vect, i - 1, i);
					/*
					 * Вызываем функцию Swap, которая меняет элементы местами
					 */
					swapped = true;
				}
			}
		} while (swapped == true);
	}

	public synchronized void Swap(Vector<Integer> vect, int index1, int index2) {
		/*
		 * Swap - функция, которая меняет элементы местами
		 */
		if (Main.stopAll == true) {
			/*
			 * Завершение, если нужно выйти из сортировки
			 */
			return;
		}
		if (vect.size() > 200) {
			/*
			 * Если количество элементов в массиве болше 200, тогда показывать
			 * анимацию бессмысленно и мы вызываем функцию swap объекта класса
			 * VecaRect с третьим параметром false, который отменяет анимацию
			 */
			veca.Swap(index1, index2, false);
		} else {
			/*
			 * Иначе с анимацией
			 */
			veca.Swap(index1, index2, true);
		}
	}

	@Override
	public void run() {
		/*
		 * Для запуска нового потока нужна функция run().
		 */
		SetName();
		/*
		 * Сначала задаем имя сортировки.
		 */
		/*Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				main.redrawG();
			}
		}, (long) 1, (long) 1);*/
		/*
		 * Назначаем новый таймер перерисовки графики каждую милисекунду
		 */
		veca.clearTime();
		/*
		 * Очищаем время, затраченное на сортировку в прошлый раз. Если такого
		 * нет, то ничего не происходит
		 */
		double thetime = System.currentTimeMillis();
		/*
		 * Записываем текущее время в переменную thetime
		 */
		Sort(v);
		/*
		 * Запускаем сортировку
		 */
		veca.setTime("" + (System.currentTimeMillis() - thetime) / 1000);
		/*
		 * По окончании сортировки устанавливаем потраченое время в
		 * соответствующее значение
		 */
		veca.DeselectAll();
		/*
		 * Снимаем выделение со всех элементов
		 */
		main.redrawG();
		/*
		 * Перерисовываем графику
		 */
		//t.cancel();
		/*
		 * Отменяем таймер
		 */
	}

	public synchronized void SetName(){
		veca.setName(SortName);
	}
	
	Main main;
	/*
	 * Переменная для хранения указателя на Main
	 */
	public VecaRect veca;
	/*
	 * Переменная для хранения объекта класса VecaRect
	 */
	public Vector<Integer> v;
	/*
	 * Переменная для хранения вектора значений
	 */
	public String SortName = "Сортировка пузырьками";
	/*
	 * Название сортировочного алгоритма
	 */

	/*
	 * Таким образом структура сортировочного класса является очень гибкой. Для
	 * того чтобы добавить в программу новый тип сортировки достаточно либо
	 * скопировать текущий код (либо унаследовать любой из сортировочных классов
	 * и поменять только функцию Sort и значение переменной SortName - Название
	 * сортировки, которое будет отображаться в программе
	 * 
	 * В Sort нужно вписать свой алгоритм сортировки, используя, если нужно,
	 * функцию Swap, которая принимает всего два параметра - индексы первого и
	 * второго элементов.
	 * 
	 * Так же нельзя забывать, что если графически какой-то элемент нужно
	 * выделить, то достаточно просто указать его индекс в функции SelectElement
	 * 
	 * В нужные места также нужно вставлять паузы функцией
	 * main.pause(Main.PAUSETIME.getValue());
	 * 
	 * В связи с тем, что структура у всех сортировочных классов одинаковая,
	 * опиисание их структур будет излишним
	 */
}