package com.example.newmusicapp

object Constants {
    // This Method create an employee
    // Arraylist and return the Arraylist
    fun getEmployeeData():ArrayList<MusicList>{
        // create an arraylist of type employee class
        val employeeList=ArrayList<MusicList>()
        val emp1= MusicList("Krishna","Whats Audio","02:0",R.raw.krishna)
        employeeList.add(emp1)
        val emp2=MusicList("Manga yhi dua main","Whats Audio","0:20",R.raw.main)
        employeeList.add(emp2)
        val emp3=MusicList("Sanu ek pal","Whats Audio","0:20",R.raw.music)
        employeeList.add(emp3)
        val emp4=MusicList("kum_kum","Whats Audio","0:20",R.raw.kum_kum)
        employeeList.add(emp4)
        val emp5=MusicList("ringtone","Whats Audio","0:20",R.raw.ringtone)
        employeeList.add(emp5)
        val emp6=MusicList("izhar","Whats Audio","0:20",R.raw.izhar)
        employeeList.add(emp6)
        val emp7=MusicList("nokia","Whats Audio","0:20",R.raw.nokia)
        employeeList.add(emp7)
        val emp8=MusicList("aaja_mere_mahi","Whats Audio","0:20",R.raw.aaja_mere_mahi)
        employeeList.add(emp8)


        return  employeeList
    }
}