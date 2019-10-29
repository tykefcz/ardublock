package com.ardublock.translator.block.funduino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class PcInt extends TranslatorBlock
{

	public PcInt(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		String gename=translator.getBlock(blockId).getGenusName();
		String ret = "";
		if (gename.startsWith("pcint_attach")) { // proc(num)
		  ret = "PCattachPin(" 
		      + this.getRequiredTranslatorBlockAtSocket(0).toCode() + ");";
		} else if (gename.startsWith("pcint_detach")) { // proc(num)
		  ret = "PCdetachPin(" 
		      + this.getRequiredTranslatorBlockAtSocket(0).toCode() + ");";
		} else if (gename.startsWith("pcint_isevent")) { // bool
		  ret = "is_pcevent()";
		} else if (gename.startsWith("pcint_evpin")) { // number
		  ret = "pcevent_pin()";
		} else if (gename.startsWith("pcint_evedge")) { // bool
		  ret = "pcevent_rise_edge()";
		} else if (gename.startsWith("pcint_evstamp")) { // number
		  ret = "pcevent_stamp()";
		} else if (gename.startsWith("pcint_micros")) { // long(long)
		  ret = "ticksToMicros("
		      + this.getRequiredTranslatorBlockAtSocket(0).toCode() + ")";
		} else if (gename.startsWith("pcint_evpop")) { // proc()
		  ret = "pcevent_pop();";
		} else if (gename.startsWith("pcint_ticks")) { // number()
		  ret = "ticks()";
		}
		translator.addHeaderFile("ArPicMtes.h");
		translator.addSetupCommand("PCbegin();");
		return ret;
	}

}
