package com.xiaomai.yyshanghu.indexfragment.storepage.method;

import java.util.List;

/**
 * Created by WANG on 17/12/8.
 */

public interface ExpandableHelpMethod<T> {

   void setListener(Object o);

   void setRefreshData(List<T> data);

   void setLoadMoveData(List<T> data);

   void insertDataByPosition(int insertPosition, List<T> dataBean);

   void removeDataByPosition(int insertPosition, List<T> dataBean);
}
