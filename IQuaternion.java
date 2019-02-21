package Algebra;

public interface IQuaternion {
public double GetItem(int i);//������� ��������� �������� ��������
public IVector GetVect();//������� ��������� ��������� ����� �����������
public IQuaternion Clone();//������� �������� ����� �������
//public IQuaternion fromElements(double q0,double q1,double q2,double q3);//������������ ������� ��� �������� ����������� �� ��� �����������
//public IQuaternion fromAngleAndAxis(double phi,IVector e);//������������ ������� ��� �������� ����������� �� ���� � ��� �������� 
//public IQuaternion fromKrylovAngles(double yaw,double pitch,double roll);//������������ ������� ��� �������� ����������� �� ����� �������
public IQuaternion Negative();//�������� ������� �����
public IQuaternion Add(IQuaternion a);//�������� �������� ������������
public IQuaternion Substract(IQuaternion a);//�������� ��������� ������������
public IQuaternion Multiply(IQuaternion a);//�������� ��������� ������������
public IQuaternion Multiply(double a);//�������� ��������� ����������� �� �����
public IQuaternion Multiply(IVector a);//�������� ��������� ����������� �� ������
public IQuaternion norm();//������� ������������ �����������
public double Length();//������� ��������� ������ 
public IQuaternion conj();//������� ��������� ������������ �����������
public IQuaternion inv();//������� ��������� �����������
public IMatrix toRotateMatrix();//������� ��������� ������� �������� �� ����������� �����������
public IQuaternion toReturnQ(IMatrix A);// ����������� ����������� �� ������� ��������
public void Print();// ����� ����������� � �������
}
