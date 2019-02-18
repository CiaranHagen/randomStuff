library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- And in the darkness... Bind them!

-- MAIN ENTITY=======================================================================
entity registerFile is
	Port(	src_S0, src_S1, src_S2, des_A0, des_A1, des_A2, clk, data_src: in std_logic;
		data: in std_logic_vector(15 downto 0);
		reg0_out, reg1_out, reg2_out, reg3_out, reg4_out, reg5_out, reg6_out, reg7_out: out std_logic_vector(15 downto 0));
end registerFile;

architecture behavioural of registerFile is
    -- COMPONENTS============================================================================
    component mux8_16bit is
	   Port(   In0, In1, In2, In3, In4, In5, In6, In7: in std_logic_vector(15 downto 0);
	           S0, S1, S2: in std_logic;
	           Z: out std_logic_vector(15 downto 0));	
	end component;
	
	component mux2_16bit is
        Port(  In0, In1: in std_logic_vector(15 downto 0);
	           S: in std_logic;
	           Z: out std_logic_vector(15 downto 0));
	end component;
	
	component decoder is
	   Port(  A0, A1, A2: in std_logic;
		      Q0, Q1, Q2, Q3, Q4, Q5, Q6, Q7: out std_logic);	
	end component;
	
	component reg8_16bit is
		Port(	 D: in std_logic_vector(15 downto 0);
			     Q: out std_logic_vector(15 downto 0);
			     load: in std_logic;
			     clk: in std_logic);
	end component;
	-- SIGNALS==================================================================================
	signal dec_outQ0, dec_outQ1, dec_outQ2, dec_outQ3, dec_outQ4, dec_outQ5, dec_outQ6, dec_outQ7, load0, load1, load2, load3, load4, load5, load6, load7: std_logic;
	signal mux2_out, mux8_out, reg0Q, reg1Q, reg2Q, reg3Q, reg4Q, reg5Q, reg6Q, reg7Q: std_logic_vector(15 downto 0);

begin
    -- INSTANTIATIONS====================================================================
	reg0: reg8_16bit port map(D => mux2_out, Q => reg0Q, load => load0, clk => clk);
	reg1: reg8_16bit port map(D => mux2_out, Q => reg1Q, load => load1, clk => clk);
	reg2: reg8_16bit port map(D => mux2_out, Q => reg2Q, load => load2, clk => clk);
	reg3: reg8_16bit port map(D => mux2_out, Q => reg3Q, load => load3, clk => clk);
	reg4: reg8_16bit port map(D => mux2_out, Q => reg4Q, load => load4, clk => clk);
	reg5: reg8_16bit port map(D => mux2_out, Q => reg5Q, load => load5, clk => clk);
	reg6: reg8_16bit port map(D => mux2_out, Q => reg6Q, load => load6, clk => clk);
	reg7: reg8_16bit port map(D => mux2_out, Q => reg7Q, load => load7, clk => clk);
	
	mux8: mux8_16bit port map( In0 => reg0Q, 
	                           In1 => reg1Q, 
	                           In2 => reg2Q, 
	                           In3 => reg3Q, 
	                           In4 => reg4Q, 
	                           In5 => reg5Q, 
	                           In6 => reg6Q, 
	                           In7 => reg7Q,
	                           S0 => src_S0, 
	                           S1 => src_S1, 
	                           S2 => src_S2,
	                           Z => mux8_out);
	                           
    mux2: mux2_16bit port map(  In0 => data,
                                In1 => mux8_out,
                                S => data_src,
                                Z => mux2_out);
                                
    dec: decoder port map(  A0 => des_A0, 
                            A1 => des_A1, 
                            A2 => des_A2,
		                    Q0 => load0, 
		                    Q1 => load1, 
		                    Q2 => load2, 
		                    Q3 => load3, 
		                    Q4 => load4, 
		                    Q5 => load5, 
		                    Q6 => load6, 
		                    Q7 => load7);
    
    -- OUTPUTS==================================================================================
    reg0_out <= reg0Q; 
    reg1_out <= reg1Q; 
    reg2_out <= reg2Q; 
    reg3_out <= reg3Q; 
    reg4_out <= reg4Q; 
    reg5_out <= reg5Q; 
    reg6_out <= reg6Q;
    reg7_out <= reg7Q;
    
end behavioural;

