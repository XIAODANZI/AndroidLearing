package com.chen.intent;
import android.os.Parcel;
import android.os.Parcelable;
/**
 * 待测试，使用Intent传值
 * @author Administrator
 *
 */
public class Person implements Parcelable {

	String name;
	int age;
	String sid;

	public Person() {
	}

	public Person(String name, int age, String sid) {
		this.name = name;
		this.age = age;
		this.sid = sid;
	}

	public Person(Parcel in) {
		System.out.println("constructor");
		name = in.readString();
		age = in.readInt();
		sid = in.readString();
		in.recycle();
	}

	public void print(){
		System.out.println("name="+ name + ", age=" + age + ", sid=" + sid);
	}
	// 描述包含在Parcelable对象排列信息的特定的对象类型。
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(age);
		dest.writeString(sid);
		System.out.println("write");
	}
	
	public static final Parcelable.Creator<Person> CREATOR = new Creator<Person>() {
		@Override
		public Person createFromParcel(Parcel source) {
			System.out.println("create");
			return new Person(source);
		}
		@Override
		public Person[] newArray(int size) {
			return new Person[size];
		}
	};

}
