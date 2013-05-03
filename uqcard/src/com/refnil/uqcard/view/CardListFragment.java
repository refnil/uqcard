package com.refnil.uqcard.view;

import java.util.ArrayList;

import com.refnil.uqcard.R;
import com.refnil.uqcard.data.CachedCardStore;
import com.refnil.uqcard.data.CachedCardStore.CachedStoreNotInitialised;
import com.refnil.uqcard.data.Card;
import com.refnil.uqcard.data.CreatureCard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CardListFragment extends Fragment {
	public CachedCardStore cardStore;
	public CardListFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.card_list,
		        container, false);
		
		ArrayList<String> listItems=new ArrayList<String>();
	    ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getActivity(),
	            android.R.layout.simple_list_item_1,
	            listItems);
	    
	    
	    try{
	    	cardStore = CachedCardStore.getStore();
	    	int i=0;
	    	while(cardStore.getCard(i)!=null)
	    	{
	    		Card card = cardStore.getCard(i);
	    		String info = card.getName().toString() + " " + card.getCost();
	    		
	    		if(card instanceof CreatureCard)
	    		{
	    			CreatureCard creature = (CreatureCard) card;
	    			info += " " + String.valueOf(creature.getAtk()) + " " + String.valueOf(creature.getDef()) + " " + String.valueOf(creature.getHp());
	    		}
	    		
	    		listItems.add(info);
	    		i++;
	    	}
	    }catch(CachedStoreNotInitialised e)
	    {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    }
		
		ListView lv = (ListView) view.findViewById(R.id.listViewCardsList);
		lv.setAdapter(adapter);
		
		return view;
	}
}