package com.iquantex.samples.shopping.listener;

import com.iquantex.phoenix.core.message.protobuf.Phoenix;
import com.iquantex.phoenix.eventpublish.core.CommittableEventBatchWrapper;
import com.iquantex.phoenix.eventpublish.core.EventHandler;
import com.iquantex.phoenix.server.eventstore.EventStoreRecord;
import com.iquantex.samples.shopping.coreapi.account.AccountPayOkEvent;
import com.iquantex.samples.shopping.coreapi.account.AccountQueryEvent;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author quail
 */
@Component
public class PopPublishHandler implements EventHandler<Phoenix.Message, Phoenix.Message> {

    /** 流行度排行内容 */
    @Getter
    public Map<String, Integer> popList = new HashMap<>();

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public CommittableEventBatchWrapper handleBatch(CommittableEventBatchWrapper batchWrapper) {
        List<EventStoreRecord<Phoenix.Message>> events = batchWrapper.getEvents();
        Iterator<EventStoreRecord<Phoenix.Message>> iterator = events.iterator();

        while (iterator.hasNext()) {
            EventStoreRecord<Phoenix.Message> event = iterator.next();
            Phoenix.Message content = event.getContent();
            String payloadClassName = content.getPayloadClassName();
            if (payloadClassName.equals(AccountQueryEvent.class.getName())) {
                String accountCode = content.getAggregateId().split("@")[2];
                if (popList.containsKey(accountCode)){
                    popList.put(accountCode, popList.get(accountCode) + 1);
                }else {
                    popList.put(accountCode, 1);
                }
                this.popList = sortMap(popList);
            }
        }
        return batchWrapper;
    }

    @Override
    public int getOrder() {
        return 0;
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

