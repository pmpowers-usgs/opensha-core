/*******************************************************************************
 * Copyright 2009 OpenSHA.org in partnership with
 * the Southern California Earthquake Center (SCEC, http://www.scec.org)
 * at the University of Southern California and the UnitedStates Geological
 * Survey (USGS; http://www.usgs.gov)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package org.opensha.sha.earthquake;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

import org.opensha.commons.data.Named;
import org.opensha.commons.data.TimeSpan;
import org.opensha.commons.geo.Region;
import org.opensha.commons.param.Parameter;
import org.opensha.commons.param.ParameterList;
import org.opensha.sha.util.TectonicRegionType;


/**
 * <p>Title: ERF (was EqkRupForecastBaseAPI)</p>
 * <p>Description: This defines the common interface that applies to both an ERF 
 * and an EpistemicListERF (the methods that are common betwen the two).</p>
 * @author : Ned Field, Nitin Gupta and Vipin Gupta
 * @created Sept 30,2004
 * @version 1.0
 */

public interface BaseERF extends Named, Serializable, Comparable<BaseERF> {
	
	/**
	 * To increase load time for applications, the name of each ERF should be stored
	 * as a public static final String called "NAME". This is the default name, and should
	 * be overridden in implementing classes.
	 */
	public static final String NAME = "Unnamed ERF";

	/**
	 * This method tells the forecast that the user is done setting parameters and that
	 * it can now prepare itself for use.  We could avoid needing this method if the 
	 * forecast updated every time a parameter was changed, but this would be very inefficient
	 * with forecasts that take a lot of time to update.  This also avoids problems associated
	 * with accidentally changing a parameter in the middle of a calculation.
	 * @return
	 */
	public void updateForecast();

	/**
	 * This method sets the time-span field
	 * @param time
	 */
	public void setTimeSpan(TimeSpan time);


	/**
	 * This method gets the time-span field
	 */
	public TimeSpan getTimeSpan();


	/**
	 * Loops over all the adjustable parameters and set parameter with the given
	 * name to the given value.
	 * First checks if the parameter is contained within the ERF adjustable parameter
	 * list or TimeSpan adjustable parameters list. If not then IllegalArgumentException is thrown.
	 * @param name String Name of the Adjustable Parameter
	 * @param value Object Parameeter Value
	 * @throws IllegalArgumentException if ERF doesn't contain parameter.
	 * value.
	 */
	public void setParameter(String name, Object value);

	/**
	 * Gets the Adjustable parameter list for the ERF
	 * @return
	 */
	public ParameterList getAdjustableParameterList();

	/**
	 * Get the region for which this forecast is applicable
	 * @return : Geographic region object specifying the applicable region of forecast
	 */
	public Region getApplicableRegion();

	/**
	 * This specifies what types of Tectonic Regions are included in the ERF
	 * @return : ArrayList<TectonicRegionType>
	 */
	public ArrayList<TectonicRegionType> getIncludedTectonicRegionTypes();



}
