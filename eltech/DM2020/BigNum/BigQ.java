package eltech.DM2020.BigNum;

import java.util.*;

/**
* Класс, который позволяет манипулировать с большими рациональными числами
* @version 0.01
* @author Аюпов Ренат, Сычев Александр
*/
public class BigQ
{
	private BigZ p; // Числитель
	private BigZ q; // Знаменатель
	
	private BigQ(){}
	
	/**
	* Конструктор, с помощью которого можно ввести большое рациональное число
	* Если p и q  не инициализированны, то бросит исключение
	*
	* @param BigZ p, q - целые числа: p - числитель, q - знаменатель
	*
	* @version 1.0
	* @author Аюпов Ренат
	*/
	public BigQ(BigZ p, BigZ q) throws IllegalArgumentException, ArithmeticException
	{
		if(p == null || q == null)
			throw new IllegalArgumentException("Неверный аргумент: числа должны быть инициализированны\n");
		this.p = p.clone();
		this.q = q.clone();
		if( q.equals( new BigZ("0") ) )
			throw new ArithmeticException("В знаменателе не может быть нуля\n");
	}
		
	/**
	* Конструктор, с помощью которого можно ввести большое рациональное число
	* Если строка src пустая или null, то бросит исключение
	* Например: из этого "-2521/-2632" сделает это "2521/2632"
	* "2521/-2632" ------> "-2521/2632"
	* "-2521/2632" ------> "-2521/2632"
	* "2521/2632" ------> "2521/2632"
	* "-2521/1" ------> "-2521"
	* "-2521/-1" ------> "2521", "2521/-1" ------> "-2521"
	* "2521/1" ------> "2521"
	* "2521/0" или "2521/-0" ------> исключение
	*
	* @param String src - строка, представляющая большое рациональное число. Её вид должен быть такой: "[числитель]/[знаменатель]". Например: -2357982579/-5617929
	*
	* @version 1.0
	* @author Сычев Александр
	*/
	public BigQ(String src) throws IllegalArgumentException, ArithmeticException
	{
		int SlashIndex;
		if(src == null)
			throw new IllegalArgumentException("Неверный аргумент: строка не может быть не инициализированной\n");
		if(src.equals(""))
			throw new IllegalArgumentException("Неверный аргумент: строка не может быть пустой\n");
		src = src.trim();
		SlashIndex = src.indexOf("/");
		if (SlashIndex == -1)
		{
			this.p = new BigZ(src);
			this.q = new BigZ("1");
		}
		else
		{
			this.p = new BigZ( src.substring(0, SlashIndex) );
			this.q = new BigZ( src.substring(SlashIndex+1, src.length()) );
		}
		if( q.equals( new BigZ("0") ) )
			throw new ArithmeticException("В знаменателе не может быть нуля\n");
	}
	
	/**
	* Вывод большого рационального числа в виде строки
	* Например: из этого "-2521/-2632" сделает это "2521/2632"
	* "2521/-2632" ------> "-2521/2632"
	* "-2521/2632" ------> "-2521/2632"
	* "2521/2632" ------> "2521/2632"
	* "-2521/1" ------> "-2521"
	* "-2521/-1" ------> "2521", "2521/-1" ------> "-2521"
	* "2521/1" ------> "2521"
	* "2521/0" или "2521/-0" ------> исключение
	*
    * @return String - представление числа в виде строки
	*
	* @version 1
	* @author Сычев Александр
	*/
	@Override
	public String toString()
	{
		return ( this.checkPositive() ? "" : "-") + this.p.abs().toString() + ( q.abs().equals(new BigZ("1")) ? "" : ("/" + q.abs().toString()) );
	}
	
	/**
	* Клонирование объекта
	*
    * @return BigQ - копия
	*
	* @version 1
	* @author Сычев Александр
	*/
	@Override
	public BigQ clone()
	{
		BigQ result = new BigQ();
		result.p = this.p.clone();
		result.q = this.q.clone();
		return result;
	}
	
	/**
	* Проверка знака большого рациональное числа
	*
    * @return boolean - знак рационального числа
	*
	* @version 1
	* @author Сычев Александр
	*/
	public boolean checkPositive()
	{
		return !(p.checkPositive() ^ q.checkPositive());
	}
	
	/**
	* Абсолютное значение рационального числа
	*
    * @return BigQ - абсолютное значение рационального числа
	*
	* @version 1
	* @author Сычев Александр
	*/
	public BigQ abs()
	{
		BigQ result = this.clone();
		result.p = result.p.abs();
		result.q = result.q.abs();
		return result;
	}
	
	/**
	* Сравнение BigQ, согласно спецификации Java
	*
    * @return эквивалентность
	*
	* @version 1
	* @author Сычев Александр
	*/
	@Override
    public boolean equals(Object otherObj) 
	{
		if (otherObj == this) return true; 
		if (otherObj == null) return false;
		if( this.getClass() != otherObj.getClass() ) return false;
		BigQ other = (BigQ)otherObj;
		return this.p.equals(other.p) && this.q.equals(other.q);
    } 
}
















