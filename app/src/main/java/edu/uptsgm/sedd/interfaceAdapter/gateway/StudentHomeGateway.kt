package edu.uptsgm.sedd.interfaceAdapter.gateway

import edu.uptsgm.sedd.application.port.GetEvaluationsOutputPort
import edu.uptsgm.sedd.application.port.StudentOutputPort

interface StudentHomeGateway: StudentOutputPort, GetEvaluationsOutputPort