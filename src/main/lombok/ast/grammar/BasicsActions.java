/*
 * Copyright (C) 2010-2012 The Project Lombok Authors.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package lombok.ast.grammar;

import lombok.ast.Comment;
import lombok.ast.Position;

public class BasicsActions extends SourceActions {
	public BasicsActions(Source source) {
		super(source);
	}
	
	public boolean checkIfKeyword(String text) {
		return text == null || !BasicsParser.KEYWORDS.contains(text);
	}
	
	public boolean logComment(String text) {
		Comment c = text.startsWith("//") ? createLineComment(text) : createBlockComment(text);
		source.registerComment(getContext(), c);
		return true;
	}
	
	public Comment createBlockComment(String text) {
		text = text.substring(2, text.length() - 2);
		Comment c = new Comment().astBlockComment(true).astContent(text);
		c.setPosition(new Position(startPos(), currentPos()));
		return c;
	}
	
	public Comment createLineComment(String text) {
		text = text.substring(2);
		Comment c = new Comment().astBlockComment(false).astContent(text);
		c.setPosition(new Position(startPos(), currentPos()));
		return c;
	}
}
