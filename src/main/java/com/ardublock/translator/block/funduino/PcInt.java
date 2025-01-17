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
		  ret = "pcAttachPin(" 
		      + this.getRequiredTranslatorBlockAtSocket(0).toCode() + ");";
		} else if (gename.startsWith("pcint_detach")) { // proc(num)
		  ret = "pcDetachPin(" 
		      + this.getRequiredTranslatorBlockAtSocket(0).toCode() + ");";
		} else if (gename.startsWith("pcint_isevent")) { // bool
		  ret = "isPcEvent()";
		} else if (gename.startsWith("pcint_evpin")) { // number
		  ret = "pcEventPin()";
		} else if (gename.startsWith("pcint_evedge")) { // bool
		  ret = "pcEventRiseEdge()";
		} else if (gename.startsWith("pcint_evstamp")) { // number
		  ret = "pcEventStamp()";
		} else if (gename.startsWith("pcint_micros")) { // long(long)
		  ret = "ticksToMicros("
		      + this.getRequiredTranslatorBlockAtSocket(0).toCode() + ")";
		} else if (gename.startsWith("pcint_evpop")) { // proc()
		  ret = "pcEventPop();";
		} else if (gename.startsWith("pcint_ticks")) { // number()
		  ret = "ticks()";
		}
		translator.addHeaderFile("ArPicMtes.h");
		translator.addSetupCommand("pcBegin();");
		return ret;
	}

}
