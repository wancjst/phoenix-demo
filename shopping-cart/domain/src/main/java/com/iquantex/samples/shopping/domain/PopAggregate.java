package com.iquantex.samples.shopping.domain;

import com.iquantex.phoenix.core.message.RetCode;
import com.iquantex.phoenix.server.aggregate.ActReturn;
import com.iquantex.phoenix.server.aggregate.CommandHandler;
import com.iquantex.phoenix.server.aggregate.EntityAggregateAnnotation;
import com.iquantex.phoenix.server.aggregate.QueryHandler;
import com.iquantex.samples.shopping.coreapi.account.AccountQueryEvent;
import com.iquantex.samples.shopping.coreapi.pop.PopCmd;
import com.iquantex.samples.shopping.coreapi.pop.PopEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author quail
 */
@EntityAggregateAnnotation(aggregateRootType = "PopAggregate")
public class PopAggregate implements Serializable {

    private static final long serialVersionUID = 4979724816614880155L;
    /** 流行度排行内容 */
    private static Map<String, Integer> popList = new HashMap<>();

    @QueryHandler(aggregateRootId = "popCode")
    public ActReturn act(PopCmd cmd) {
        return ActReturn.builder().retCode(RetCode.SUCCESS)
                .event(new PopEvent(cmd.getPopCode(),popList)).build();
    }


    @CommandHandler(aggregateRootId = "accountCode")
    public ActReturn act(AccountQueryEvent event){
        String accountCode = event.getAccountCode();
        if (popList.containsKey(accountCode)){
            popList.put(accountCode, popList.get(accountCode) + 1);
        }else {
            popList.put(accountCode, 1);
        }
        this.popList = sortMap(popList);
        return ActReturn.builder().retCode(RetCode.SUCCESS)
                .event(new PopEvent(event.getAccountCode(),popList)).build();
    }

    private static Map sortMap(Map oldMap) {
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldMap.entrySet());
        list.sort(Comparator.comparingInt(Map.Entry::getValue));
        Map newMap = new LinkedHashMap();
        for (int i = list.size()-1; i >= 0; i--) {
            newMap.put(list.get(i).getKey(),list.get(i).getValue());
        }
        return newMap;
    }
}