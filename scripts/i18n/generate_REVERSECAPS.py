#!/usr/bin/python -u

# Run this script from within its own directory

import re, os

en_GB_re = re.compile( "\\.en\\.properties" );

en_GB_files = []
en_REVERSECAPS_files = []

i18n_dir = "../../src/resources/i18n"

for fl in os.listdir( i18n_dir ):
	if en_GB_re.search( fl ):
		en_GB_files.append( file( i18n_dir + "/" + fl, 'r' ) )
		en_REVERSECAPS_files.append( file( i18n_dir + "/" + fl.replace(
			".en.", ".en_RC." ), 'w' ) )

for i in range( len( en_GB_files ) ):
    
    for line in en_GB_files[i].readlines():
    
        split_line = line.split( '=', 1 )
    
        if isinstance( split_line, list ) and len( split_line ) == 2:
            new_line = "%s=%s" % ( split_line[0], split_line[1].swapcase() )
        elif line[0] == "#":
            new_line = line
        else:
            new_line = line.swapcase()
        
        en_REVERSECAPS_files[i].write( new_line )
        
    en_REVERSECAPS_files[i].close()
    en_GB_files[i].close()

