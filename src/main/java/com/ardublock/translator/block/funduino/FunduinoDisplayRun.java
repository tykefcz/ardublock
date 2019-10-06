package com.ardublock.translator.block.funduino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class FunduinoDisplayRun extends TranslatorBlock
{

	public FunduinoDisplayRun(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		String ret = "\tfunduino.step();\n";
		translator.addHeaderFile("Funduino.h");
		translator.addDefinitionCommand("Funduino funduino = Funduino();");
		translator.addSetupCommand("funduino.begin();");
		return ret;
	}
}

