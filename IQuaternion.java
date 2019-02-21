package Algebra;

public interface IQuaternion {
public double GetItem(int i);//функция получения значения элемента
public IVector GetVect();//функция получения векторной части кватерниона
public IQuaternion Clone();//функция создания копии объекта
//public IQuaternion fromElements(double q0,double q1,double q2,double q3);//производящая функция для создания кватерниона по его компонентам
//public IQuaternion fromAngleAndAxis(double phi,IVector e);//производящая функция для создания кватерниона по углу и оси поворота 
//public IQuaternion fromKrylovAngles(double yaw,double pitch,double roll);//производящая функция для создания кватерниона по углам Крылова
public IQuaternion Negative();//Оператор унарный минус
public IQuaternion Add(IQuaternion a);//Оператор сложения кватернионов
public IQuaternion Substract(IQuaternion a);//Оператор вычитания кватернионов
public IQuaternion Multiply(IQuaternion a);//оператор умножения кватернионов
public IQuaternion Multiply(double a);//оператор умножения кватерниона на число
public IQuaternion Multiply(IVector a);//оператор умножения кватерниона на вектор
public IQuaternion norm();//функция нормирования кватерниона
public double Length();//функция получения модуля 
public IQuaternion conj();//функция получения сопряженного кватерниона
public IQuaternion inv();//функция обращения кватерниона
public IMatrix toRotateMatrix();//функция получения матрицы вращения из компонентов кватерниона
public IQuaternion toReturnQ(IMatrix A);// возвращение кватерниона по матрице вращения
public void Print();// вывод кватерниона в консоль
}
