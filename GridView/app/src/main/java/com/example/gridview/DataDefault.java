package com.example.gridview;

import java.util.ArrayList;
import java.util.List;

public class DataDefault {
    public static List<DoGiaDung> getList(){
        DoGiaDung quat = new DoGiaDung("Quạt", R.drawable.quat, "Quạt đứng", 1830000);
        DoGiaDung bepHongNgoai = new DoGiaDung("Bếp hồng ngoại", R.drawable.bephongngoai, "Bếp hồng ngoại", 1830000);
        DoGiaDung maySinhTo = new DoGiaDung("máy sinh tố", R.drawable.maysinhto, "máy sinh tố", 1830000);
        DoGiaDung noiChien = new DoGiaDung("nồi chiên", R.drawable.noichien, "nồi chiên", 1830000);
        DoGiaDung noiComDien = new DoGiaDung("nồi cơm điện", R.drawable.noicomdien, "nồi cơm điện", 1830000);
        List<DoGiaDung> doGiaDungList = new ArrayList<>();
        doGiaDungList.add(quat);
        doGiaDungList.add(bepHongNgoai);
        doGiaDungList.add(maySinhTo);
        doGiaDungList.add(noiChien);
        doGiaDungList.add(noiComDien);
        return doGiaDungList;
    }
}
