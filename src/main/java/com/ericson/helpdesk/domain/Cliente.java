package com.ericson.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Cliente extends Pessoa{
	
	private List<Chamado> chamados = new ArrayList<>();

}
