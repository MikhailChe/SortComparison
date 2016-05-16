package sort;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import acm.graphics.GLabel;
import acm.graphics.GRect;

public class VecaRect {

	public VecaRect(Main m) {
		/*
		 * Конструктор класса VecaRect. Принимает указатель на объект класса
		 * Main
		 */
		main = m;
		NOS++;
		/*
		 * Записываем в переменную main указатель на объект класса Main и
		 * прибавляем к статичной переменной NOS еденицу
		 */
	}

	public VecaRect(Main m, Integer noel) {
		/*
		 * Конструктор класса VecaRect. Принимает указатель на объект класса
		 * Main и количество элементов
		 */
		main = m;
		NOS++;
		initArray(noel, false, false);
		/*
		 * Записываем в переменную main указатель на объект класса Main,
		 * прибавляем к статичной переменной NOS еденицу и инициализируем
		 * массив, передавая соответствующие параметры
		 */
	}

	public synchronized void setTime(String time) {
		/*
		 * setTime - функция для установки времени сортировки Данная функция
		 * является синхронизированой во избежание повреждения данных
		 */
		SortTime.setLabel(time);
		SortTime.setVisible(true);
		SortTime.setColor(timeColor);
		SortTime.sendToFront();
		/*
		 * Приравниваем надписи SortTime значение time Делаем её видимой
		 * Присваиваем цвет timeColor; Выкидываем надпись на передний план
		 */
	}

	public void clearTime() {
		/*
		 * Функция очистки времени сортировки Применяется когда начинается новая
		 * сортировка Просто делает надпись с временем невидимой
		 */
		SortTime.setVisible(false);
	}

	public synchronized void SelectElement(int index) {
		/*
		 * Функция графического выделения элемента. По своей сути она просто
		 * добавляет в массив(вектор) Selected элемент с индексом элемента,
		 * который нужно выделить
		 */
		Selected.add(index);
	}

	public synchronized boolean lockUpdateRects() {
		/*
		 * Функция блокировки перерисовки массива. Применяется для того чтобы
		 * анимация не дергалась.
		 */
		if (islockedUpdateRects())
			return false;
		lockUpdateRects = true;
		return true;
		/*
		 * Сначала проверяем установлено ли уже значение в true. Если значение
		 * установлено в true, то функция возвращает false и ничего не делает.
		 * Возврат булевого значения полезен для того чтобы знать, была ли уже
		 * заблокирована перерисовка или нет. Если lockUpdateRects было false,
		 * то меняем его в true и возвращаем true.
		 */
	}

	public synchronized boolean unlockUpdateRects() {
		/*
		 * Похожая на lockUpdateRects() функция, только делает всё наоборот.
		 */
		if (!islockedUpdateRects())
			return false;
		lockUpdateRects = false;
		return true;
	}

	public synchronized boolean islockedUpdateRects() {
		/*
		 * Функция которая проверяет установлена ли блокировка или нет
		 */
		return lockUpdateRects;
	}

	public synchronized boolean isSelected(int index) {
		/*
		 * Проверяет установлено ли графическое выделение на элемент или нет
		 */
		for (int i = 0; i < Selected.size(); i++) {
			if (Selected.get(i).equals(index)) {
				return true;
			}
		}
		return false;
		/*
		 * Проходимся по массиву. Если находим наш элемент - возвращаем true.
		 * Если не находим - возвращаем false
		 */
	}

	public synchronized void DeselectAll() {
		/*
		 * Функция для снятия графического выделения со всех элементов
		 */
		Selected.clear();
	}

	public synchronized void initArray(Integer noel, boolean isSorted,
			boolean upSorted) {
		/*
		 * Функция инициализации массива. Принимает параметры: noel: количество
		 * элементов в массиве isSorted: отсортирован ли массив upSorted:
		 * отсортирован ли массив вверх или вниз
		 */
		Numbers.clear();
		/*
		 * Очищаем массив с значениями
		 */
		if (isSorted) {
			/*
			 * Если нужно сортировать
			 */
			if (upSorted) {
				/*
				 * И сортировать нужно вверз
				 */
				for (int i = 0; i < noel; i++) {
					/*
					 * Добавляем подряд элементы
					 */
					Numbers.add(i);
				}
			} else {
				/*
				 * Если вниз, то элементы добавляются в обратном порядке
				 */
				for (int i = 0; i < noel; i++) {
					Numbers.add(noel - i);
				}
			}
		} else {
			/*
			 * Если не сортировать, то добавляются случайные числа
			 */
			for (int i = 0; i < noel; i++) {
				Numbers.add((int) ((double) Math.random() * (double) noel));
			}
		}
	}

	public synchronized void initArray(Vector<Integer> arr) {
		/*
		 * Функция инициализации массива, принимает один параметр: массив,
		 * который нужно вставить. Данная функция используется, когда нужно
		 * создать одинаковые массивы
		 */
		Numbers.clear();
		Numbers.addAll(arr);
		/*
		 * Сначала очищаем массив, а потом добавляем все элементы из
		 * передаваемого массива
		 */
	}

	public synchronized void initRects(Integer NIQ) {
		/*
		 * Функция инициализации графики. Принимает один параметр - NIQ: номер в
		 * очереди. Параметр NIQ нужен для того, чтобы знать, куда конкретно
		 * вставлять элементы графики
		 */
		Rects.clear();
		Values.clear();
		vecs.clear();
		/*
		 * Очщиаем все прямоугольники и надписи со значениями
		 */
		clearTime();
		/*
		 * Очищаем время сортировки
		 */
		for (int i = 0; i < Numbers.size(); i++) {
			/*
			 * Для каждого из элеметов массива делаем следующее
			 */
			GRect r = new GRect(0, 0, 0, 0);
			/*
			 * Создаем новый прямоугольник
			 */
			r.setFilled(true);
			/*
			 * Переключаем его в режим заливания - площадь внутри контура
			 * прямоугольника будет залита цветом
			 */
			Rects.add(r);
			/*
			 * Добавляем его в вектор прямоугольников
			 */
			GLabel l = new GLabel("");
			/*
			 * Создаем пустой лэйбл (надпись)
			 */
			Values.add(l);
			/*
			 * Добавляем её в вектор надписей
			 */
		}
		Name = new GLabel("");
		/*
		 * Создаем пустое имя сотировки
		 */
		updateRects(NIQ);
		/*
		 * Обновляем графику, чтобы всё приняло свои места
		 */
	}

	public synchronized void updateRects(Integer NIQ) {
		/*
		 * Функция обновления графики
		 */

		if (Numbers.size() != Rects.size()) {
			/*
			 * Если количество чисел не совпадает с количеством прямоугольников
			 * - нужно заново инициализировать графику. Инициализируем, выходим
			 * из функции.
			 */
			initRects(NIQ);
			return;
		}
		if (islockedUpdateRects()) {
			/*
			 * Если графику обновлять запрещено, то выходим из функции
			 */
			return;
		}
		if (!lockUpdateRects()) {
			/*
			 * Если заблокировать обновление графики не получилось, то это
			 * значит, что графика заблокирована, выходим из функции
			 */
			return;
		}

		for (int i = 0; i < Rects.size(); i++) {
			/*
			 * Для каждого из прямоугольников делаем:
			 */
			Double BarHeight = (double) main.getHeight()
					* (double) Numbers.get(i) / (double) Rects.size()
					/ (double) NOS;
			/*
			 * Вычисляем высоту прямоугольника
			 */
			Rects.get(i).setLocation(
					(double) i * (double) main.getWidth()
							/ (double) Rects.size(),
					(double) main.getHeight()
							* (double) ((double) NIQ + (double) 1)
							/ (double) NOS - (double) BarHeight);
			/*
			 * Устанавливаем положение прямоугольника: По X координате -
			 * вычисляем какой это прямоугольник по порядку, умножаем порядковое
			 * число на частное от щирины холста и количества прямоугольников
			 * Частным являеися ширина прямоугольника.
			 * 
			 * По Y - умножаем высоту холста на порядковый номер массива (NIQ) и
			 * делим на количество массивов. Вчитаем высоту прямоугольника
			 */
			Rects.get(i).setSize(
					(double) main.getWidth() / (double) Rects.size(),
					(double) BarHeight);
			/*
			 * Устанавливаем ширину прямоугольника как ширину холста деленая на
			 * количество прямоугольников, а высота уже пощитана - BarHeight
			 */
			if (isSelected(i)) {
				/*
				 * Если прямоугольник нужно выделить - заливаем одним цветом,
				 * если нет, то другим
				 */
				Rects.get(i).setFillColor(selectedColor);
			} else {
				Rects.get(i).setFillColor(usualColor);
			}

			/*
			 * Если размер надписи значения очень маленький, то мы его не
			 * показываем
			 */
			if ((1.0 * main.getWidth() / Numbers.size() <= 10) || (Values.get(i)
					.getHeight() > 1.0 * main.getHeight() / NOS)) {
				Values.get(i).setVisible(false);
			} else {
				Values.get(i).setVisible(true);
				Values.get(i).setLabel(Numbers.get(i).toString());
				Values.get(i)
						.setFont(
								new Font("Sans-Serif", Font.PLAIN,
										(int) ((double) main.getWidth()
												/ (double) Numbers.size()
												* 0.75)));
				if (!SortTime.isVisible())
					Values.get(i).sendToFront();

				Values.get(i).setLocation(
						((double) i + 0.5) * (double) main.getWidth()
								/ (double) Numbers.size()
								- Values.get(i).getWidth() * 0.5,
						(double) main.getHeight() / (double) NOS
								* (double) (NIQ + 1));
			}
		}

		/*
		 * Если название сортировки не нулевое, то перемещаем в нужную позицию
		 */
		if (Name != null) {
			Name.setLocation(0, (double) Name.getHeight()
					+ (double) main.getHeight() / (double) NOS * (double) NIQ);
		}
		/*
		 * Если время сортировки нужно показывать, то перемещаем его в нужную
		 * позицию, задаем шрифт, и выбрасываем на передний план
		 */
		if (SortTime.isVisible()) {
			SortTime.setFont(new Font("Serif", Font.BOLD | Font.ITALIC,
					(int) (main.getHeight() / (double) NOS / 2.0)));
			SortTime.setLocation(0,
					main.getHeight() / (double) NOS * (NIQ + 0.5)
							+ SortTime.getHeight() / 2.0);
			SortTime.sendToFront();
		}
		unlockUpdateRects();
		/*
		 * Разблокировываем обновление графики
		 */
	}

	public synchronized void setName(String name) {
		if (Name != null) {
			Name.setLabel(name);
		} else {
			Name = new GLabel(name);
		}
	}

	public void Swap(int index1, int index2, boolean animation) {
		/*
		 * Функция анимированой перестановки элементов местами
		 */
		lockUpdateRects();
		/*
		 * Блокируем обновление графики данного массива, чтобы не дергалась
		 * анимация
		 */
		if (animation && Main.PAUSETIME.getValue() > 0) {
			/*
			 * Высчитываем X координаты, помещаем перемещаемые прямоугольники и
			 * надписи на передний план, и запускаем анимацию из 100 кадров.
			 */
			double pos1 = Rects.get(index1).getX();
			double pos2 = Rects.get(index2).getX();
			Rects.get(index1).sendToFront();
			Rects.get(index2).sendToFront();
			Values.get(index1).sendToFront();
			Values.get(index2).sendToFront();
			for (int i = 0; i < 100; i++) {
				Rects.get(index1).move((pos2 - pos1) / 100.0, (i - 50) / 50.0);
				Values.get(index1).move((pos2 - pos1) / 100.0, (i - 50) / 50.0);
				Rects.get(index2).move((pos1 - pos2) / 100.0, (50 - i) / 50.0);
				Values.get(index2).move((pos1 - pos2) / 100.0, (50 - i) / 50.0);
				main.pause(Main.PAUSETIME.getValue() / 60);
			}
		}
		/*
		 * После анимации меняем местами сами значения
		 */
		Integer bzz = Numbers.get(index1);
		Numbers.set(index1, Numbers.get(index2));
		Numbers.set(index2, bzz);
		unlockUpdateRects();
		/*
		 * Разблокировываем обновление графики
		 */
	}

	public Vector<Integer> Numbers = new Vector<Integer>();
	/*
	 * Вектор из значений элементов
	 */
	public Vector<GRect> Rects = new Vector<GRect>();
	/*
	 * Вектор из прямоугольников
	 */
	public Vector<GLabel> Values = new Vector<GLabel>();
	/*
	 * Вектор из надписей значений
	 */
	public Vector<Integer> Selected = new Vector<Integer>();
	/*
	 * Вектор из индексов элементов для выделения
	 */
	public GLabel Name = new GLabel("");
	/*
	 * Надпись, содеражащая имя сортировки
	 */
	public GLabel SortTime = new GLabel("");
	/*
	 * Надпись о времени, потраченом на сортировку
	 */
	public static Integer NOS = 0;
	/*
	 * Статичная переменная, содержащая количество активных массивов(векторов)
	 */
	public static Vector<VecaRect> vecs = new Vector<VecaRect>();
	private Main main;
	/*
	 * Переменная для хранения указателя на Main
	 */
	private static Color usualColor = Color.GREEN;
	private static Color selectedColor = Color.RED;
	private static Color timeColor = Color.RED;
	/*
	 * Цвета, используемые в графике
	 */
	private boolean lockUpdateRects = false;
	/*
	 * Переменная блокировки олбновления графики
	 */
}